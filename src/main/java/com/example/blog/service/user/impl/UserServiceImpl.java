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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    // FIXME : 로그인 구현체 , 토큰 발급기능과 분리 처리
    @Override
    public Optional<UserDto> findUserByEmail(UserDto userDto) {
        return userRepository.findUserByEmail(userDto.getUserEmail());
    }

    /**
     * 이메일 중복 여부를 확인
     *
     * @param requestDto UserRequestDto.JoinUser
     * @return UserResponseDto.JoinUser
     */
    @Override
    @Transactional
    public UserResponseDto.JoinUser saveUser(UserRequestDto.JoinUser requestDto) {

        if (isEmailExist(requestDto.getUserEmail())){
            throw new BusinessExceptionHandler(ErrorCode.EMAIL_ALREADY_EXIST.getMessage(), ErrorCode.EMAIL_ALREADY_EXIST);
        }

        User defaultUser = User.setDefaultUser(requestDto, passwordEncoder);
        defaultUser = userRepository.save(defaultUser);
        if (defaultUser.getId() < 0) {
           throw new BusinessExceptionHandler(ErrorCode.INSERT_ERROR.getMessage(), ErrorCode.INSERT_ERROR);
        }
        log.info("[USER_SERVICE] METHOD_SAVE_USER :: " + SuccessCode.INSERT_SUCCESS.getMessage() + " USER_ID : " + defaultUser.getId());

        return new UserResponseDto.JoinUser(defaultUser.getId(), "defaultUrl");
    }

    /**
     * 이메일 중복 여부를 확인
     *
     * @param email
     * @return true | false
     */
    private boolean isEmailExist(String email) {
        Optional<UserDto> byEmail = userRepository.findUserByEmail(email);
        return byEmail.isPresent(); // 존재 true , 부재 false
    }






}
