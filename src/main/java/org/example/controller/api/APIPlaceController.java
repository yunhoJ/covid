package org.example.controller.api;

import org.example.constant.PlaceType;
import org.example.dto.APIDataResponse;
import org.example.dto.PlaceDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequestMapping("/api")
//@RestController
public class APIPlaceController {
    
    @GetMapping("/places")
    public APIDataResponse<List<PlaceDTO>> getPlaces(){
        return APIDataResponse.of(List.of(PlaceDTO.of(
                PlaceType.COMMON,
                "빵집",
                "행복로",
                "010-3213-3131",
                10,
                "신장개업"

        )));
    }
    
    @PostMapping("/places")
    public Boolean createPlace(){
        return true;
    }
    
    @GetMapping("places/{placeId}")
    public APIDataResponse<PlaceDTO> getPlace(@PathVariable Integer placeId){
        if(placeId.equals(2)){
            return APIDataResponse.of(null);
        }

        return APIDataResponse.of(PlaceDTO.of(
                PlaceType.COMMON,
                "빵집",
                "행복로",
                "010-3213-3131",
                10,
                "신장개업"

        ));
    }

    @PutMapping("places/{placeId}")
    public Boolean modifyPlace(@PathVariable Integer placeId){
        return true;
    }
    
    @DeleteMapping("/places/{placeId}")
    public Boolean removePlace(@PathVariable Integer placeId){
        return true;
    }
}
