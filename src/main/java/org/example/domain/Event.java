package org.example.domain;

import lombok.Data;
import org.example.constant.EventStatus;

import java.time.LocalDateTime;

@Data
public class Event {
    private Long id;

    private Long placeId;
    private String eventName;
    private EventStatus eventStatus;
    private LocalDateTime eventStartDatetime;
    private LocalDateTime eventEndDatetime;
    private Integer currentNumberOfPeople;
    private Integer capacity;
    private String meno;
//    메타데이터
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
}
