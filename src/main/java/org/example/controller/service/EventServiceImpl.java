package org.example.controller.service;

import lombok.RequiredArgsConstructor;
import org.example.constant.EventStatus;
import org.example.dto.EventDTO;
import org.example.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service //서비스 컨포넌트로 등록
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    @Override
    public List<EventDTO> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    ) {
        return List.of(
                EventDTO.of(
                        1L,
                        "오전운동",
                        EventStatus.OPENED,
                        LocalDateTime.parse("2021-01-01T00:00:00"),
                        LocalDateTime.of(2021,1,1,0,0,0),
                        0,
                        24,
                        "마스크 쓰세요!",
                        LocalDateTime.now(),
                        LocalDateTime.now()
        ));
    }

    public Optional<EventDTO> getEvent(Long eventId) {
        return Optional.empty();
    }

    public boolean createEvent(EventDTO eventDTO) {
        return true;
    }

    public boolean modifyEvent(Long eventId, EventDTO eventDTO){
        return true;
    }

    public boolean removeEvent(Long eventId){
        return true;
    }
}
