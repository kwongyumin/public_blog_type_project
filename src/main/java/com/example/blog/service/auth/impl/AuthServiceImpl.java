package com.example.blog.service.auth.impl;

import com.example.blog.common.codes.ErrorCode;
import com.example.blog.common.util.TokenUtils;
import com.example.blog.config.exception.BusinessExceptionHandler;
import com.example.blog.dto.auth.AuthRequestDto;
import com.example.blog.dto.auth.AuthResponseDto;
import com.example.blog.dto.user.UserDto;
import com.example.blog.repository.user.UserRepository;
import com.example.blog.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    /**
     * 입력된 유저 정보를 검증하여 토큰을 발급한다.
     *
     * @param requestDto AuthRequestDto.GenerateUserToken
     * @param roleUser String
     * @return AuthResponseDto.GenerateUserToken
     *   NOTE : 비회원 토큰 발급 추가 예정 ->
     */
    @Override
    public AuthResponseDto.GenerateUserToken generateUserToken(AuthRequestDto.GenerateUserToken requestDto, String roleUser) {

        // #1. 이메일 -> 유저정보 검증
        UserDto user = userRepository.findUserByEmail(requestDto.getUserEmail()).orElseThrow(
                () -> new BusinessExceptionHandler(ErrorCode.NOT_FOUND_USER.getMessage(), ErrorCode.NOT_FOUND_USER));

        // #2. 패스워드 검증
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
     * 유저 입력 비밀번호 확인
     *
     * @param inputPassword String
     * @param encryptedPassword String
     * @return true | false
     */
    private boolean passwordValidator(String inputPassword, String encryptedPassword) {
        if (passwordEncoder.matches(inputPassword, encryptedPassword)){
            return true;
        } else {
            return false;
        }
    }



}
