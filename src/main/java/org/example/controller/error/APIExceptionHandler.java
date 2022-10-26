package org.example.controller.error;

import org.example.constant.ErrorCode;
import org.example.dto.APIErrorResponse;
import org.example.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//restController를 사용하는놈만 봄 - api들만
@RestControllerAdvice(annotations = RestController.class)
public class APIExceptionHandler {
    @ExceptionHandler //general exception 이 생길경우
    public ResponseEntity<APIErrorResponse> general(GeneralException e){
        ErrorCode errorCode= e.getErrorCode();
        HttpStatus status = errorCode.isClientSideError()?
                HttpStatus.BAD_REQUEST:HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(status)
                .body(APIErrorResponse.of(
                        false,errorCode,errorCode.getMessage(e)
                ));
    }

    @ExceptionHandler //전체적인 에러가 생길경우
    public ResponseEntity<APIErrorResponse> exception(Exception e){
        ErrorCode errorCode= ErrorCode.INTERNAL_ERROR;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(status)
                .body(APIErrorResponse.of(
                        false,errorCode,errorCode.getMessage(e)
                ));
    }
}
