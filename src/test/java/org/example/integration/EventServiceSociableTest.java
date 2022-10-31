package org.example.integration;

import org.example.controller.service.EventServiceImpl;
import org.example.dto.EventDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;

@SpringBootTest
public class EventServiceSociableTest {

    @Autowired
    private EventServiceImpl sut;

    @DisplayName("검색 조건없이 이벤트 검색하면 전체 결과를 출력해서 보내줌")
    @Test
    void givenNothing_whenSearchingEvents_thenReturnALLEvents() {
        //Given

        //When
        List<EventDTO> list = sut.getEvents(null, null, null, null, null);
        //Then
        assertThat(list).hasSize(0);


    }
}