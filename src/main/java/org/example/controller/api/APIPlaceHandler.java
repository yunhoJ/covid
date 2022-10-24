package org.example.controller.api;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.function.ServerResponse.created;
import static org.springframework.web.servlet.function.ServerResponse.ok;

@Component
public class APIPlaceHandler   {

    public ServerResponse getPlaces(ServerRequest request) {
        return ok().body(List.of("place1","place2"));
    }

    public ServerResponse createPlace(ServerRequest request){
        return created(URI.create("api/place/1")).body(true);// TODO: 1은 구현시 다시 넣어줌
    }

    public ServerResponse getPlace( ServerRequest request){
        return ok().body("place "+request.pathVariable("placeId"));
    }

    public ServerResponse modifyPlace(ServerRequest request){
        return ok().body(true);
    }

    public ServerResponse removePlace(ServerRequest request){
        return ok().body(true);
    }
}
