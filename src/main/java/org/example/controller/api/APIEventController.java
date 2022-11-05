package org.example.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.ErrorCode;
import org.example.constant.EventStatus;
import org.example.controller.service.EventService;
import org.example.controller.service.EventServiceImpl;
import org.example.dto.*;
import org.example.exception.GeneralException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
//@RequestMapping("/api")
//@RestController
@Validated
@Slf4j
public class APIEventController {

    private final EventServiceImpl eventService;
    @GetMapping("/events")
    public APIDataResponse<List<EventResponse>> getEvents(
            @Positive Long placeId,
            @Size(min=2) String eventName,
            EventStatus eventStatus,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventStartDatetime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventEndDatetime
    ) {
        List<EventResponse> responses =eventService
                .getEvents( //list<EventDTO>에서 List<EventResponse> 로 넣을수 없음
                        //.map을 이용해 하나씩 꺼내서 from함수로 넣은후 타입 변환
                        placeId,
                        eventName,
                        eventStatus,
                        eventStartDatetime,
                        eventEndDatetime)
                .stream().map(EventResponse::from).toList();
        return APIDataResponse.of(responses);


    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/events")
    public APIDataResponse<Boolean> createEvent(@Valid @RequestBody EventRequest request){

        log.debug("보고 싶은 값 {}",request);
        boolean result=eventService.createEvent(request.toDTO(request));
        return APIDataResponse.of(result);
    }

    @GetMapping("events/{eventId}")
    public APIDataResponse<EventResponse> getEvent(@PathVariable Long eventId){
        if (eventId.equals(2L)) {
            return APIDataResponse.empty();
        }
        return APIDataResponse.of(EventResponse.of(
                1L,
                "오후 운동",
                EventStatus.OPENED,
                LocalDateTime.parse("2021-01-01T13:00:00"),
                LocalDateTime.of(2021,1,1,16,0,0),
                0,
                24,
                "마스크 꼭 착용하세요"));


    }

    @PutMapping("events/{eventId}")
    public APIDataResponse<Boolean> modifyEvent(@PathVariable Integer eventId){
        return APIDataResponse.empty();
    }

    @DeleteMapping("/events/{eventId}")
    public APIDataResponse<Boolean> removeEvent(@PathVariable Integer eventId){
        return APIDataResponse.empty();
    }

}
