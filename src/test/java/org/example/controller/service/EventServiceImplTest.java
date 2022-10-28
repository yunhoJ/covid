package org.example.controller.service;

import org.assertj.core.api.Assertions;
import org.example.constant.EventStatus;
import org.example.dto.EventDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EventServiceImplTest {
    private EventServiceImpl sut;
                //@SpringbootTest, @Autowired로 주입할수 있지만 오래 걸림
    @BeforeEach // junit5의 구현체 만들기 - sut의 구현체가 없음
    void setSut(){
        sut=new EventServiceImpl();
    }
    // 리스트 조회
    @DisplayName("검색 조건없이 이벤트 검색하면 전체 결과를 출력해서 보내줌")
    @Test
    void givenNothing_whenSearchingEvents_thenReturnALLEvents(){
        //Given

        //When
        List< EventDTO > list =sut.getEvents(null,null,null,null,null);
        //Then
        assertThat(list).hasSize(2);
    }

    @DisplayName("검색 조건과 함께 이벤트 검색하면 검색된 결고를 결과를 출력해서 보내줌")
    @Test
    void givenSearchParams_whenSearchingEvents_thenReturnALLEvents(){
        //Given
        Long placeId = 1L;
        String eventName="오전운동";
        EventStatus eventStatus = EventStatus.OPENED;
        LocalDateTime eventStartDateTime = LocalDateTime.of(2021,1,1,0,0);
        LocalDateTime eventEndDateTime = LocalDateTime.of(2021,1,1,0,0);
        //When
        List< EventDTO > list =sut.getEvents(placeId,eventName,eventStatus,eventStartDateTime,eventEndDateTime);
        //Then
        assertThat(list)
                .hasSize(1)
                .allSatisfy(event -> {
                    assertThat(event)
                            .hasFieldOrPropertyWithValue("placeId",placeId)
                            .hasFieldOrPropertyWithValue("eventName",eventName)
                            .hasFieldOrPropertyWithValue("eventStatus",eventStatus);
                    assertThat(event.eventStartDatetime()).isAfterOrEqualTo(eventStartDateTime);
                    assertThat(event.eventEndDatetime()).isBeforeOrEqualTo(eventEndDateTime);
                });
    }

    @DisplayName("이벤트 id로 존재하는 이벤트를 조회 하면 해당 이벤트 정보를 출력해야한다 ")
    @Test
    void givenEventId_whenSearchingExistingEvent_thenReturnsEvents(){
        long eventId = 1L;
        //Given
        EventDTO eventDTO= createEventDTO(1L,"오전운동",true);

        //When
        Optional<EventDTO> result = sut.getEvent(eventId);
        //Then
        assertThat(result).hasValue(eventDTO);
    }

    @DisplayName("이벤트 id로 이벤트 조회하면빈정보를 출력하여 보여줌")
    @Test
    void givenEventId_thenSearching_thenReturn(){
        long eventId =2L;
        //Given
//        given(eventRepository.findEcent(eventId).willReturn(Optional.empty()));
        //When
        Optional<EventDTO> result = sut.getEvent(eventId);
        //Then
        assertThat(result).isEmpty();
    }

    @DisplayName("이벤트 아이디와 정보를 주면 이벤트 정보 수정후 결과 true 보여줌")
    @Test
    void givenIdAndInfo_whenModifyEvent_thenTrue(){
        //Given
        long eventId = 1L;
        EventDTO eventDTO= createEventDTO(1L,"오후운동",false);
        //When
        Boolean result = sut.modifyEvent(eventId,eventDTO);
        //Then
        assertThat(result).isTrue();
    }

    @DisplayName("이벤트 아이디가 없으면  이벤트 정보 수정불가와 결과 false 보여줌")
    @Test
    void givenAndInfo_whenModifyEvent_thenFalse(){

        //Given
        EventDTO eventDTO= createEventDTO(1L,"오후운동",false);
        //When
        Boolean result = sut.modifyEvent(null,eventDTO);
        //Then
        assertThat(result).isFalse();
    }

    @DisplayName("이벤트 정보가 없으면  이벤트 정보 수정불가와 결과 false 보여줌")
    @Test
    void givenId_whenModifyEvent_thenFalse(){
        long eventId = 1L;
        //Given
        //When
        Boolean result = sut.modifyEvent(eventId,null);
        //Then
        assertThat(result).isFalse();
    }

    @DisplayName("아이디 주면 이벤트 삭제후 true")
    @Test
    void givenId_whenRemoveEvent_thenTrue(){
        //Given
        long eventId = 1L;
        //When
        Boolean result = sut.removeEvent(eventId);
        //Then
        assertThat(result).isTrue();
    }

    @DisplayName("아이디 주면 없으면 이벤트 삭제후 true")
    @Test
    void givenId_whenRemoveEvent_thenFalse(){
        //Given
        //When
        Boolean result = sut.removeEvent(null);
        //Then
        assertThat(result).isFalse();
    }


    /**
     * 중복코드 제거
     * @param placeId
     * @param eventName
     * @param isMorning
     * @return
     */
    private EventDTO createEventDTO(long placeId, String eventName, boolean isMorning){
        String hourStart = isMorning ? "09" :"13";
        String hourEnd = isMorning ? "12" :"16";

        return createEventDTO(placeId,eventName,EventStatus.OPENED,
                LocalDateTime.parse("2021-01-01T%s:00:00".formatted(hourStart)),
                LocalDateTime.parse("2021-01-01T%s:00:00".formatted(hourEnd)));
    }
    //TestEventDTO
    public static EventDTO createEventDTO(
            long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    ) {
        return  EventDTO.of(
                placeId,
                eventName,
                eventStatus,
                eventStartDatetime,
                eventEndDatetime,
                0,
                24,
                "마스크 쓰세요!",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}