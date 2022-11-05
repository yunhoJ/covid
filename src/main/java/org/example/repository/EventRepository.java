package org.example.repository;

import org.example.constant.EventStatus;
import org.example.domain.Event;
import org.example.dto.EventDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
//TODO: 인스턴스 생성 편의를 위해 임시로 defalut 사용, 구현 완성시 삭제 예정
public interface EventRepository extends JpaRepository<Event,Long>{
    default List<EventDTO> findEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDateTime,
            LocalDateTime eventEndDateTime
    ) {
        return List.of();
    }

    default Optional<EventDTO> findEvent(Long eventId) {
        return Optional.empty();
    }

    default boolean insertEvent(EventDTO eventDTO) {
        return false;
    }

    default boolean updateEvent(Long eventId, EventDTO eventDTO) {
        return false;
    }

    default boolean deleteEvent(Long eventId) {
        return false;
    }
}
