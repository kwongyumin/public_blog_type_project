package com.example.blog.service.user.impl;

import com.example.blog.common.codes.SuccessCode;
import com.example.blog.dto.user.UserRequestDto;
import com.example.blog.dto.user.UserResponseDto;
import com.example.blog.model.user.User;
import com.example.blog.repository.user.UserRepository;
import com.example.blog.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserResponseDto.LoginUser loginUser(UserRequestDto.LoginUser requestDto) {
        UserResponseDto.LoginUser result = null;
        // FIXME : 유저 확인 및 토큰 발급처리

        return result;
    }

    @Override
    @Transactional
    public UserResponseDto.JoinUser saveUser(UserRequestDto.JoinUser requestDto) {
        UserResponseDto.JoinUser result = null;
        User defaultUser = User.setDefaultUser(requestDto);
        // NOTE : exceptionHandler 테스트 필요
        defaultUser = userRepository.save(defaultUser);
        log.info("[USER_SERVICE] METHOD_SAVE_USER :: " + SuccessCode.INSERT_SUCCESS.getMessage() + " USER_ID : " + defaultUser.getId());
        // FIXME : 로그인 유도 URL 필요 여부 홗인 후 수정
        result = new UserResponseDto.JoinUser(defaultUser.getId(), "defaultUrl");
        return result;
    }





}
