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
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;

    @Override
    public AuthResponseDto.GenerateUserToken generateToken(AuthRequestDto.GenerateUserToken requestDto, String roleUser) {
        // FIXME : 회원정보 관련 ErrorCode 추가 필요
        UserDto user = userRepository.findLoginUserByEmail(requestDto.getUserEmail()).orElseThrow(
                () -> new BusinessExceptionHandler(ErrorCode.SELECT_ERROR.getMessage(), ErrorCode.SELECT_ERROR));

        String authToken = TokenUtils.generateJwtToken(user);
        if (authToken == null || authToken.isEmpty()) {
            throw new BusinessExceptionHandler(ErrorCode.AUTH_TOKEN_IS_NULL.getMessage(), ErrorCode.AUTH_TOKEN_IS_NULL);
        }
        return new AuthResponseDto.GenerateUserToken(user.getUserId(),authToken);
    }
}
