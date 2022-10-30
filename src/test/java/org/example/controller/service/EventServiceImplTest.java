package org.example.controller.service;

import org.assertj.core.api.Assertions;
import org.example.constant.ErrorCode;
import org.example.constant.EventStatus;
import org.example.dto.EventDTO;
import org.example.exception.GeneralException;
import org.example.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {
   @InjectMocks //목을 주입할 녀석 ( 그릇 )
   private EventServiceImpl sut;
   @Mock    //목으로 주입할녀석 ( 음식 )
   private EventRepository eventRepository;
    // 리스트 조회
    @DisplayName("검색 조건없이 이벤트 검색하면 전체 결과를 출력해서 보내줌")
    @Test
    void givenNothing_whenSearchingEvents_thenReturnALLEvents(){
        //Given
        // 목이 하는 일을 넣어줌
        BDDMockito.given(eventRepository.findEvents(null,null,null,null,null))
                .willReturn(List.of(createEventDTO(1L,"오전 운동",true),
                        createEventDTO(1L,"오후 운동",false)
                ));
        //When
        List< EventDTO > list =sut.getEvents(null,null,null,null,null);
        //Then
        assertThat(list).hasSize(2);
//        verify(eventRepository).findEvents(null,null,null,null,null); //아래코드와 같은 역할
        then(eventRepository).should().findEvents(null,null,null,null,null);

    }

    @DisplayName("검색 조건과 함께 이벤트 검색하면 검색된 결고를 결과를 출력해서 보내줌")
    @Test
    void givenSearchParams_whenSearchingEvents_thenReturnALLEvents(){
        //Given
        Long placeId = 1L;
        String eventName="오전 운동";
        EventStatus eventStatus = EventStatus.OPENED;
        LocalDateTime eventStartDateTime = LocalDateTime.of(2021,1,1,0,0);
        LocalDateTime eventEndDateTime = LocalDateTime.of(2021,1,1,0,0);

        given(eventRepository.findEvents(placeId,eventName,eventStatus,eventStartDateTime,eventEndDateTime))
                .willReturn(List.of(createEventDTO(1L,"오전 운동",eventStatus,eventStartDateTime,eventEndDateTime)));
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
        then(eventRepository).should().findEvents(placeId,eventName,eventStatus,eventStartDateTime,eventEndDateTime);
    }

    @DisplayName("이벤트를 검색하는데 에러가 발생한 경우 줄서기 프로젝트 기본 에러로 전환하여 예외 던진다")
    @Test
    void givenDataException_whenSearchingEvents_thenReturnGeneralExecption(){
        //Given
        RuntimeException e = new RuntimeException("Test") ;
        BDDMockito.given(eventRepository.findEvents(any(),any(),any(),any(),any()))
                .willThrow(e);

        //When
        Throwable throwable=catchThrowable(()->sut.getEvents(any(),any(),any(),any(),any()));
        //Then
        assertThat(throwable)
                .isInstanceOf(GeneralException.class)
                        .hasMessageContaining(ErrorCode.DATA_ACCESS_ERROR.getMessage());
//        verify(eventRepository).findEvents(null,null,null,null,null); //아래코드와 같은 역할
        then(eventRepository).should().findEvents(any(),any(),any(),any(),any());

    }

    @DisplayName("이벤트 id로 존재하는 이벤트를 조회 하면 해당 이벤트 정보를 출력해야한다 ")
    @Test
    void givenEventId_whenSearchingExistingEvent_thenReturnsEvents(){
        //Given
        long eventId = 1L;
        EventDTO eventDTO= createEventDTO(1L,"오전운동",true);
        given(eventRepository.findEvent(eventId)).willReturn(Optional.of(eventDTO));
        //When
        Optional<EventDTO> result = sut.getEvent(eventId);
        //Then
        assertThat(result).hasValue(eventDTO);
        then(eventRepository).should().findEvent(eventId);
    }

    @DisplayName("이벤트 id로 이벤트 조회하면 빈 정보를 출력하여 보여줌")
    @Test
    void givenEventId_whenSearching_thenReturn(){
        long eventId =2L;
        //Given
        given(eventRepository.findEvent(eventId)).willReturn(Optional.empty());
        //When
        Optional<EventDTO> result = sut.getEvent(eventId);
        //Then
        assertThat(result).isEmpty();
        then(eventRepository).should().findEvent(eventId);
    }
    @DisplayName("이벤트 정보를 주명 이벤트를 생성하고 결과를 true로 보여줌")
    @Test
    void givenEvent_whencreateEvent_thenTrue(){
        //Given
        EventDTO dto = createEventDTO(1L,"오후 운동",true);
        given(eventRepository.insertEvent(dto)).willReturn(true);

        //When
        boolean result = sut.createEvent(dto);
        //Then
        assertThat(result).isTrue();
        then(eventRepository).should().insertEvent(dto);
    }
    @DisplayName("이벤트 정보가 없으면  이벤트를 생성하고 결과를 false로 보여줌")
    @Test
    void givenNull_whenCreateEvent_thenFalse(){
        //Given
        given(eventRepository.insertEvent(null)).willReturn(false);
        //When
        boolean result = sut.createEvent(null);
        //Then
        assertThat(result).isFalse();
        then(eventRepository).should().insertEvent(null);
    }

    @DisplayName("이벤트 아이디와 정보를 주면 이벤트 정보 수정후 결과 true 보여줌")
    @Test
    void givenIdAndInfo_whenModifyEvent_thenTrue(){
        //Given
        long eventId = 1L;
        EventDTO eventDTO= createEventDTO(1L,"오후운동",false);
        given(eventRepository.updateEvent(eventId,eventDTO)).willReturn(true);
        //When
        Boolean result = sut.modifyEvent(eventId,eventDTO);
        //Then
        assertThat(result).isTrue();
        then(eventRepository).should().updateEvent(eventId,eventDTO);
    }

    @DisplayName("이벤트 아이디가 없으면  이벤트 정보 수정불가와 결과 false 보여줌")
    @Test
    void givenAndInfo_whenModifyEvent_thenFalse(){
        //Given
        EventDTO eventDTO= createEventDTO(1L,"오후 운동",false);
        given(eventRepository.updateEvent(null,eventDTO)).willReturn(false);
        //When
        Boolean result = sut.modifyEvent(null,eventDTO);
        //Then
        assertThat(result).isFalse();
        then(eventRepository).should().updateEvent(null,eventDTO);
    }

    @DisplayName("이벤트 정보가 없으면  이벤트 정보 수정불가와 결과 false 보여줌")
    @Test
    void givenId_whenModifyEvent_thenFalse(){
        //Given
        long eventId = 1L;
        given(eventRepository.updateEvent(eventId,null)).willReturn(false);
        //When
        Boolean result = sut.modifyEvent(eventId,null);
        //Then
        assertThat(result).isFalse();
        then(eventRepository).should().updateEvent(eventId,null);
    }

    @DisplayName("아이디 주면 이벤트 삭제후 true")
    @Test
    void givenId_whenRemoveEvent_thenTrue(){
        //Given
        long eventId = 1L;
        given(eventRepository.deleteEvent(eventId)).willReturn(true);
        //When
        Boolean result = sut.removeEvent(eventId);
        //Then
        assertThat(result).isTrue();
        then(eventRepository).should().deleteEvent(eventId);
    }

    @DisplayName("아이디 주면 없으면 이벤트 삭제후 true")
    @Test
    void givenId_whenRemoveEvent_thenFalse(){
        //Given
        given(eventRepository.deleteEvent(null)).willReturn(false);
        //When
        Boolean result = sut.removeEvent(null);
        //Then
        assertThat(result).isFalse();
        then(eventRepository).should().deleteEvent(null);
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