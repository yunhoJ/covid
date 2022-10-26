package org.example.dto;

import org.example.constant.PlaceType;

public record PlaceDTO(
        PlaceType placeType,
        String placeName,
        String address,
        String phoneNumber,
        Integer capacity,
        String meno) {

    public static PlaceDTO of(
            PlaceType placeType,
            String placeName,
            String address,
            String phoneNumber,
            Integer capacity,
            String meno) {
        return new PlaceDTO (placeType,placeName,address,phoneNumber,capacity,meno);
    }

}
