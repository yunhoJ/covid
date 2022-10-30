package org.example.dto;

import org.example.constant.EventStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

public record EventRequest(
        @Positive @NotNull Long placeId,
        @NotBlank String eventName,
        @NotNull EventStatus eventStatus,
        @NotNull LocalDateTime eventStartDatetime,
        @NotNull LocalDateTime eventEndDatetime,
        @NotNull @PositiveOrZero Integer currentNumberOfPeople,
        @NotNull @Positive Integer capacity,
        String meno

) {
    public static EventRequest of(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime,
            Integer currentNumberOfPeople,
            Integer capacity,
            String meno

    ) {
        return new EventRequest(
                placeId,
                eventName,
                eventStatus,
                eventStartDatetime,
                eventEndDatetime,
                currentNumberOfPeople,
                capacity,
                meno

        );
    }

    public EventDTO toDTO(EventRequest eventRequest) {
        return EventDTO.of(
                eventRequest.placeId,
                eventRequest.eventName,
                eventRequest.eventStatus,
                eventRequest.eventStartDatetime,
                eventRequest.eventEndDatetime,
                eventRequest.currentNumberOfPeople,
                eventRequest.capacity,
                eventRequest.meno,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
