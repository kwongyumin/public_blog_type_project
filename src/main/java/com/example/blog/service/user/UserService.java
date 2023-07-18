package com.example.blog.service.user;


import com.example.blog.dto.user.UserRequestDto;
import com.example.blog.dto.user.UserResponseDto;

public interface UserService {

    /*
        유저 정보 저장 (회원가입 처리 및 토큰 발급)
     */
    UserResponseDto.JoinUser saveUser(UserRequestDto.JoinUser requestDto);

}
