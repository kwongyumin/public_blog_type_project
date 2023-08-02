package com.example.blog.service.user;


import com.example.blog.dto.user.UserDto;
import com.example.blog.dto.user.UserRequestDto;
import com.example.blog.dto.user.UserResponseDto;

import java.util.Optional;

public interface UserService {

    /*
        FIXME: 로그인 처리 인터페이스 -> 토큰발급 처리와 분리
     */
    Optional<UserDto> loginUser(UserDto userDto);

    /*
        유저 정보 저장 (회원가입 처리 및 토큰 발급)
     */
    UserResponseDto.JoinUser saveUser(UserRequestDto.JoinUser requestDto);

}
