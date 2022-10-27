package org.example.dto;

import org.assertj.core.api.Assertions;
import org.example.constant.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class APIDataResponseTest {
    @DisplayName("데이터가 있을때 성공응답 생성")
    @Test
    void test(){
        //Given
        String data= "test data";

        //When
        APIDataResponse<String> response= APIDataResponse.of(data);
        //Then
        Assertions.assertThat(response)
                .isNotNull()
                .hasFieldOrPropertyWithValue("success",true)
                .hasFieldOrPropertyWithValue("errorCode",ErrorCode.OK.getCode())
                .hasFieldOrPropertyWithValue("message",ErrorCode.OK.getMessage())
                .hasFieldOrPropertyWithValue("data",data);
    }

    @DisplayName("데이터가 없을때 성공응답 생성")
    @Test
    void nullTest(){
        //Given
        //When
        APIDataResponse<String> response= APIDataResponse.empty();
        //Then
        Assertions.assertThat(response)
                .isNotNull()
                .hasFieldOrPropertyWithValue("success",true)
                .hasFieldOrPropertyWithValue("errorCode",ErrorCode.OK.getCode())
                .hasFieldOrPropertyWithValue("message",ErrorCode.OK.getMessage())
                .hasFieldOrPropertyWithValue("data",null);
    }
}