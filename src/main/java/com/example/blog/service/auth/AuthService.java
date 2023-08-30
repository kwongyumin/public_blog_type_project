package com.example.blog.service.auth;

import com.example.blog.dto.auth.AuthRequestDto;
import com.example.blog.dto.auth.AuthResponseDto;

public interface AuthService {


    /**
     *  DESC : 토큰발급 처리 , 비회원 토큰발급 처리에 대한 확장성을 고려 -> role param 추가
     */
    AuthResponseDto.GenerateUserToken generateUserToken(AuthRequestDto.GenerateUserToken requestDto, String roleUser);

    /**
     *  DESC : 토큰발급 처리 , 카카오톡 정보를 기반으로 유저의 토큰 발급 및 회원가입을 처리
     */
    AuthResponseDto.GenerateUserToken generateKakaoToken(String code);
}
