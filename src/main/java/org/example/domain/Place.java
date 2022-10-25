package org.example.domain;

import lombok.Data;
import org.example.constant.PlaceType;


import java.time.LocalDateTime;

@Data
public class Place {
    private Long id;

    private PlaceType placeType;
    private String placeName;
    private String address;
    private String phoneNumber;
    private Integer capacity;
    private String meno;
//    메타데이터
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
}
