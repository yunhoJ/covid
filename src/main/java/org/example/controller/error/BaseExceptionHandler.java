package org.example.controller.error;

import org.example.constant.ErrorCode;
import org.example.dto.APIErrorResponse;
import org.example.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@ControllerAdvice //전체 컨트롤러의 동작을 감지
public class BaseExceptionHandler {
    @ExceptionHandler //general exception 이 생길경우
    public ModelAndView general(GeneralException e){
        ErrorCode errorCode=e.getErrorCode();
        HttpStatus status= errorCode.isClientSideError()?
                HttpStatus.BAD_REQUEST:HttpStatus.INTERNAL_SERVER_ERROR;
        return new ModelAndView("error", Map.of(
                "statusCode",status.value(),
                "errorCode",errorCode,
                "message",errorCode.getMessage(e)
        ),status
        );
    }

    @ExceptionHandler //전체적인 에러가 생길경우
    public ModelAndView exception(Exception e){
        ErrorCode errorCode=ErrorCode.INTERNAL_ERROR;
        HttpStatus status= HttpStatus.INTERNAL_SERVER_ERROR;
        return new ModelAndView("error", Map.of(
                "statusCode",status.value(),
                "errorCode",errorCode,
                "message",errorCode.getMessage(e)
        ),status
        );
    }
}
