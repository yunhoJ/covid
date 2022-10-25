package org.example.domain;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Admin {
    private Long id;

    private String email;
    private String nickname;
    private String password;
    private String phoneNumber;
    private String meno;
//    메타데이터
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
}
