package org.example.controller.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

import static org.springframework.web.servlet.function.RequestPredicates.path;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class APIPlaceRouter {

    @Bean
    public RouterFunction<ServerResponse> placeRouter(){
        return route().nest(path("/api/places"),builder -> builder
                .GET("",request -> ServerResponse.ok().body(List.of("place1","place2")))
                .POST("",request -> ServerResponse.ok().body(true))
                .GET("/{placeId}",request -> ServerResponse.ok().body("place "+request.pathVariable("placeId")))
                .PUT("/{placeId}",request -> ServerResponse.ok().body(true))
                .DELETE("/{placeId}",request -> ServerResponse.ok().body(true))
        ).build();
    }
}
