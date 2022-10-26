package org.example.controller.api;

import org.example.constant.ErrorCode;
import org.example.dto.APIErrorResponse;
import org.example.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class APIEventController {

    @GetMapping("/events")
    public List<String> getEvents() {
        throw new GeneralException("테스트 메시지");
//        return List.of("event1","event2");
    }

    @PostMapping("/events")
    public Boolean createEvent(){
        return true;
    }

    @GetMapping("events/{eventId}")
    public String getEvent(@PathVariable Integer eventId){
        throw new RuntimeException("runtime");
//        return "event"+eventId;
    }

    @PutMapping("events/{eventId}")
    public Boolean modifyEvent(@PathVariable Integer eventId){
        return true;
    }

    @DeleteMapping("/events/{eventId}")
    public Boolean removeEvent(@PathVariable Integer eventId){
        return true;
    }
    @ExceptionHandler // 해당 클래스의 에러를 컨트롤함
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
}
