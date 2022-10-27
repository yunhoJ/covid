package org.example.dto;

import org.example.constant.PlaceType;

public record PlaceResponse(
         PlaceType placeType,
         String placeName,
         String address,
         String phoneNumber,
         Integer capacity,
         String meno
    ) {
    public static PlaceResponse of(
            PlaceType placeType, String placeName, String address, String phoneNumber, Integer capacity, String meno) {
        return new PlaceResponse(placeType, placeName, address, phoneNumber, capacity, meno);
    }
}
