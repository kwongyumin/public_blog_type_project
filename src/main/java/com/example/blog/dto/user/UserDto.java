package com.example.blog.dto.user;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Getter
@AllArgsConstructor
public class UserDto {

    // 사용자 시퀀스
    private Long userId;
    // 사용자 이름
    private String userName;
    // 사용자 아이디
    private String email;
    // 사용자 패스워드
    private String userPassword;



}