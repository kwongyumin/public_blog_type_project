package com.example.blog.service.auth.impl;

import com.example.blog.common.codes.ErrorCode;
import com.example.blog.common.util.TokenUtils;
import com.example.blog.config.exception.BusinessExceptionHandler;
import com.example.blog.dto.auth.AuthRequestDto;
import com.example.blog.dto.auth.AuthResponseDto;
import com.example.blog.dto.auth.OAuthInfoResponse;
import com.example.blog.dto.user.UserDto;
import com.example.blog.model.user.User;
import com.example.blog.repository.user.UserRepository;
import com.example.blog.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RequestOAuthInfo requestOAuthInfo;

    /**
     * 입력된 유저 정보를 검증하여 토큰을 발급한다.
     *
     * @param requestDto AuthRequestDto.GenerateUserToken
     * @param roleUser String
     * @return AuthResponseDto.GenerateUserToken
     *   FIXME  :: 비회원 토큰 발급 추가 예정 ->
     */
    @Override
    @Transactional
    public AuthResponseDto.GenerateUserToken generateUserToken(AuthRequestDto.GenerateUserToken requestDto, String roleUser) {

        // #1. 이메일 -> 유저정보 검증
        UserDto user = userRepository.findUserByEmail(requestDto.getUserEmail()).orElseThrow(
                () -> new BusinessExceptionHandler(ErrorCode.NOT_FOUND_USER.getMessage(), ErrorCode.NOT_FOUND_USER));

        // #2. 패스워드 검증 -> 매치가 된다면 true
        if (!passwordValidator(requestDto.getPassword(), user.getUserPassword())){
            throw new BusinessExceptionHandler(ErrorCode.NOT_VALID_PASSWORD.getMessage(), ErrorCode.NOT_VALID_PASSWORD);
        }

        // #3. 토큰생성
        String authToken = TokenUtils.generateJwtToken(user);
        if (authToken == null || authToken.isEmpty()) {
            throw new BusinessExceptionHandler(ErrorCode.AUTH_TOKEN_IS_NULL.getMessage(), ErrorCode.AUTH_TOKEN_IS_NULL);
        }

        return new AuthResponseDto.GenerateUserToken(user.getUserId(),authToken);
    }


    /**
     * 카카오톡 정보를 기반으로 유저 토큰발급 및 회원가입 처리.
     *
     * @param requestDto AuthRequestDto.GenerateUserTokenFromKakao
     * @return AuthResponseDto.GenerateUserToken
     */
    @Override
    @Transactional
    public AuthResponseDto.GenerateUserToken generateUserTokenFromKakao(AuthRequestDto.GenerateUserTokenFromKakao requestDto) {
        // #1. 카카오 정보 기반 유저정보 조회
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfo.request(requestDto);
        // #2. OAuth 응답 정보 기반 유저 정보 조회 및 토큰생성 처리
        return generateUserTokenFromOAuth(oAuthInfoResponse);
    }

    /**
     * 네이버 정보를 기반으로 유저 토큰발급 및 회원가입 처리.
     *
     * @param requestDto AuthRequestDto.GenerateUserTokenFromNaver
     * @return AuthResponseDto.GenerateUserToken
     */
    @Override
    @Transactional
    public AuthResponseDto.GenerateUserToken generateUserTokenFromNaver(AuthRequestDto.GenerateUserTokenFromNaver requestDto) {
        // #1. 네이버 정보 기반 유저정보 조회
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfo.request(requestDto);
        // #2. OAuth 응답 정보 기반 유저 정보 조회 및 토큰생성 처리
        return generateUserTokenFromOAuth(oAuthInfoResponse);
    }

    /**
     * OAuth 응답 정보 기반 유저 정보 조회 및 토큰생성 처리 공통부
     *
     * @param oAuthInfoResponse OAuthInfoResponse
     * @return AuthResponseDto.GenerateUserToken
     */

    private AuthResponseDto.GenerateUserToken generateUserTokenFromOAuth(OAuthInfoResponse oAuthInfoResponse) {
        // 카카오 또는 네이버 인증 서버로부터 조회한 유저 이메일 -> 유저정보 조회
        // FIXME : 회원 정보 존재하지 않을 시 , 회원가입 처리
        UserDto user = userRepository.findUserByEmail(oAuthInfoResponse.getUserEmail()).orElseThrow(
                () -> new BusinessExceptionHandler(ErrorCode.NOT_FOUND_USER.getMessage(), ErrorCode.NOT_FOUND_USER));

        // 토큰생성
        String authToken = TokenUtils.generateJwtToken(user);
        if (authToken == null || authToken.isEmpty()) {
            throw new BusinessExceptionHandler(ErrorCode.AUTH_TOKEN_IS_NULL.getMessage(), ErrorCode.AUTH_TOKEN_IS_NULL);
        }

        return new AuthResponseDto.GenerateUserToken(user.getUserId(), authToken);
    }


    /**
     * 유저 입력 비밀번호 확인
     *
     * @param inputPassword String
     * @param encryptedPassword String
     * @return true | false
     */
    private boolean passwordValidator(String inputPassword, String encryptedPassword) {
        return passwordEncoder.matches(inputPassword, encryptedPassword);
    }




}
