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
        return eventRepository.findEvents(placeId, eventName, eventStatus, eventStartDatetime, eventEndDatetime);
    }

    public Optional<EventDTO> getEvent(Long eventId) {
        return eventRepository.findEvent(eventId);
    }

    public boolean createEvent(EventDTO eventDTO) {
        return eventRepository.insertEvent(eventDTO);
    }

    public boolean modifyEvent(Long eventId, EventDTO eventDTO){
        return eventRepository.updateEvent(eventId,eventDTO);
    }

    public boolean removeEvent(Long eventId){
        return eventRepository.deleteEvent(eventId);
    }
}
