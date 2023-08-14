package com.example.blog.service.user;


import com.example.blog.dto.user.UserDto;
import com.example.blog.dto.user.UserRequestDto;
import com.example.blog.dto.user.UserResponseDto;

import java.util.Optional;

public interface UserService {

    /**
     *  DESC : jwt 필터 내에서 유저 정보 검증에 대한 요청을 처리
     */
    Optional<UserDto> loginUser(UserDto userDto);

    /**
     *  DESC : 유저정보 저장 처리 (회원가입)
     */
    UserResponseDto.JoinUser saveUser(UserRequestDto.JoinUser requestDto);

}
