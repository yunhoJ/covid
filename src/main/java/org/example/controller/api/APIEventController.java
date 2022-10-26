package org.example.controller.api;

import org.example.exception.GeneralException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class APIEventController {

    @GetMapping("/events")
    public List<String> getEvents() throws Exception {
        throw new HttpRequestMethodNotSupportedException("405 스프링 에러 테스트 ");
//        return List.of("event1","event2");
    }

    @PostMapping("/events")
    public Boolean createEvent(){
        return true;
    }

    @GetMapping("events/{eventId}")
    public String getEvent(@PathVariable Integer eventId){
        throw new GeneralException("test");// 내가 만든 익셉션인데 runtimeException 상속 받아서 throws가 필요 없을거같음
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

}
