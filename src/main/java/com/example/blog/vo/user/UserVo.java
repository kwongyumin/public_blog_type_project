package com.example.blog.vo.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@AllArgsConstructor
public class UserVo {

    // NOTE : read-only user-data

    // 사용자 시퀀스
    private Long userId;
    // 사용자 이름
    private String userName;
    // 사용자 아이디
    private String email;
    // 사용자 패스워드
    private String userPassword;



}

