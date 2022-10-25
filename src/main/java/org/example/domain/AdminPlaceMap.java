package org.example.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminPlaceMap {
    private Long id;

    private Long adminId;
    private Long placeId;

//    메타데이터
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
}
