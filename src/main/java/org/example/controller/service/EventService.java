package org.example.controller.service;


import org.example.constant.EventStatus;
import org.example.dto.EventDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 이벤트 서비스
 * @author yunho
 */
public interface EventService {
    /**
     * 리스트 조회 - 검색어를 받아서 이벤트 리스트 반환
     * @param placeId 장소
     * @param eventName 이벤트 이름
     * @param eventStatus 이벤트 상태
     * @param eventStartDatetime 시작시간
     * @param eventEndDatetime 종료시간
     * @return List<EventDTO>
     */
    List<EventDTO> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    );
}
