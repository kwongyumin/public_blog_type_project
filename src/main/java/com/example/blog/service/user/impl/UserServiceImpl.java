package com.example.blog.service.user.impl;

import com.example.blog.common.codes.ErrorCode;
import com.example.blog.common.codes.SuccessCode;
import com.example.blog.config.exception.BusinessExceptionHandler;
import com.example.blog.dto.user.UserDto;
import com.example.blog.dto.user.UserRequestDto;
import com.example.blog.dto.user.UserResponseDto;
import com.example.blog.model.user.User;
import com.example.blog.repository.user.UserRepository;
import com.example.blog.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // FIXME : 로그인 구현체 , 토큰 발급기능과 분리 처리
    @Override
    public Optional<UserDto> loginUser(UserDto userDto) {
        return userRepository.findLoginUserByEmail(userDto.getUserEmail());
    }

    @Override
    @Transactional
    public UserResponseDto.JoinUser saveUser(UserRequestDto.JoinUser requestDto) {
        User defaultUser = User.setDefaultUser(requestDto);
        defaultUser = userRepository.save(defaultUser);
        if (defaultUser.getId() < 0) {
           throw new BusinessExceptionHandler(ErrorCode.INSERT_ERROR.getMessage(), ErrorCode.INSERT_ERROR);
        }
        log.info("[USER_SERVICE] METHOD_SAVE_USER :: " + SuccessCode.INSERT_SUCCESS.getMessage() + " USER_ID : " + defaultUser.getId());

        return new UserResponseDto.JoinUser(defaultUser.getId(), "defaultUrl");
    }






}
