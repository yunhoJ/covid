package org.example.controller;

import org.example.constant.PlaceType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @GetMapping("/places")
    public ModelAndView adminPlaces(
            PlaceType placeType,
            String placeName,
            String address
            //@Requestparam 기본값 required true 없으면 false
    ){
        Map<String, Object> map= new HashMap<>();
        map.put("placeType",placeType);
        map.put("placeName",placeName);
        map.put("address",address);

        return new ModelAndView("admin/places",map);
    }

    @GetMapping("/places/{placeId}")
    public String adminPlacesDetail(@PathVariable Integer placeId){
        return "admin/places-detail";
    }

    @GetMapping("/events")
    public String adminEvents(){
        return "admin/events";
    }

    @GetMapping("/events/{eventsId}")
    public String adminEventsDetail(@PathVariable Integer eventId){
        return "admin/events-detail";
    }
}
