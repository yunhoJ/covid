package org.example.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ErrorCodeTest {
    @DisplayName("예외를 받으면, 예외 메시지가 포함된 메시지 출력")
    @ParameterizedTest(name = "[{index}] {0} ===> {1}")
    @MethodSource
    void givenExceptionWithMessage_whenGettingMessage_thenReturnsMessage(ErrorCode errorCode,String expected){
        //Given
        Exception e=new Exception("this is test message");
        //When
        String result =errorCode.getMessage(e);
        //Then
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> givenExceptionWithMessage_whenGettingMessage_thenReturnsMessage(){
        return Stream.of(
                arguments(ErrorCode.OK,"ok - this is test message"),
                arguments(ErrorCode.BAD_REQUEST,"bad request - this is test message"),
                arguments(ErrorCode.SPRING_BAD_REQUEST,"spring_bad request - this is test message"),
                arguments(ErrorCode.INTERNAL_ERROR,"internal error - this is test message"),
                arguments(ErrorCode.SPRING_INTERNAL_ERROR,"Spring-detected internal error - this is test message"),
                arguments(ErrorCode.VALIDATION_ERROR,"validationError - this is test message"),
                arguments(ErrorCode.DATA_ACCESS_ERROR,"Data access error - this is test message")
        );
    }

    @DisplayName("에러 메시지를 받으면, 해당 에러 메시지를 출력")
    @ParameterizedTest(name = "[{index}] \"{0}\" ===> {1}")
    @MethodSource
    void givenMessage_whenGettingMessage_thenReturnsMessage(String input,String expected){
        //When
        String result =ErrorCode.BAD_REQUEST.getMessage(input);
        //Then
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> givenMessage_whenGettingMessage_thenReturnsMessage(){
        return Stream.of(
                arguments(null,ErrorCode.BAD_REQUEST.getMessage()),
                arguments("",ErrorCode.BAD_REQUEST.getMessage()),
                arguments(" ",ErrorCode.BAD_REQUEST.getMessage()),
                arguments("에러 코드 테스트","에러 코드 테스트")
        );
    }
    @DisplayName("toString 호출 포맷")
    @Test
    void test(){
        String result = ErrorCode.INTERNAL_ERROR.toString();
        assertThat(result).isEqualTo("INTERNAL_ERROR (20000)");
    }
}