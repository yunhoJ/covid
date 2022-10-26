package org.example.controller.error;

import org.example.constant.ErrorCode;
import org.example.dto.APIErrorResponse;
import org.example.exception.GeneralException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//restController를 사용하는놈만 봄 - api들만
@RestControllerAdvice(annotations = RestController.class)
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler //general exception 이 생길경우
    public ResponseEntity<Object> general(GeneralException e,WebRequest request){
        ErrorCode errorCode= e.getErrorCode();
        HttpStatus status = errorCode.isClientSideError()?
                HttpStatus.BAD_REQUEST:HttpStatus.INTERNAL_SERVER_ERROR;
        return super.handleExceptionInternal(
                e,
                APIErrorResponse.of(false,errorCode.getCode(),errorCode.getMessage(e)),
                HttpHeaders.EMPTY,
                status,
                request
        );
    }

    @ExceptionHandler //전체적인 에러가 생길경우
    public ResponseEntity<Object> exception(Exception e,WebRequest request){
        ErrorCode errorCode= ErrorCode.INTERNAL_ERROR;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return super.handleExceptionInternal(
                e,
                APIErrorResponse.of(false,errorCode.getCode(),errorCode.getMessage(e)),
                HttpHeaders.EMPTY,
                status,
                request
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorCode errorCode= status.is4xxClientError()?
                ErrorCode.SPRING_BAD_REQUEST:ErrorCode.SPRING_INTERNAL_ERROR;

        return super.handleExceptionInternal(
                ex,
                APIErrorResponse.of(false,errorCode.getCode(),errorCode.getMessage(ex)),
                headers,
                status,
                request
        );
    }
}
