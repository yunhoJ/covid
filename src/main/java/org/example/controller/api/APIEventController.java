package org.example.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.constant.ErrorCode;
import org.example.constant.EventStatus;
import org.example.controller.service.EventService;
import org.example.controller.service.EventServiceImpl;
import org.example.dto.*;
import org.example.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class APIEventController {

//    private final EventServiceImpl eventService;
    @GetMapping("/events")
    public APIDataResponse<List<EventResponse>> getEvents() {
        return APIDataResponse.of(List.of(EventResponse.of(
                1L,
                "오후 운동",
                EventStatus.OPENED,
                LocalDateTime.parse("2021-01-01T13:00:00"),
                LocalDateTime.of(2021,1,1,16,0,0),
                0,
                24,
                "마스크 꼭 착용하세요")));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/events")
    public APIDataResponse<Boolean> createEvent(){

        return APIDataResponse.of(true);
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
