package org.example.controller;

import org.example.constant.EventStatus;
import org.example.dto.EventResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/events")
@Controller
public class EventController {

    @GetMapping("")
    public ModelAndView events(){
        Map<String,Object>map= new HashMap<>();
        //TODO: 임시데이터 추후 삭제 예정
        map.put("events", List.of(EventResponse.of(
                1L,
                "오후 운동",
                EventStatus.OPENED,
                LocalDateTime.of(2021,1,1,13,0,0),
                LocalDateTime.of(2021,1,1,16,0,0),
                0,
                24,
                "마스크 작용하세요"),EventResponse.of(21L,
                "오후 운동",
                EventStatus.OPENED,
                LocalDateTime.of(2021,1,1,13,0,0),
                LocalDateTime.of(2021,1,1,16,0,0),
                0,
                24,
                "마스크 작용하세요"
                )
        ));
        return new ModelAndView("event/index",map) ;
    }

    @GetMapping("/{eventId}")
    public ModelAndView eventDetail(@PathVariable Integer eventId){
        Map<String,Object>map= new HashMap<>();

        map.put("events", List.of(EventResponse.of(
                1L,
                "오후 운동",
                EventStatus.OPENED,
                LocalDateTime.of(2021,1,1,13,0,0),
                LocalDateTime.of(2021,1,1,16,0,0),
                0,
                24,
                "마스크 작용하세요")));
        return new ModelAndView("event/detail",map) ;
    }
}
