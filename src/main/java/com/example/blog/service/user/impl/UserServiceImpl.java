package com.example.blog.service.user.impl;

import com.example.blog.common.response.ApiResponse;
import com.example.blog.dto.user.UserRequestDto;
import com.example.blog.dto.user.UserResponseDto;
import com.example.blog.model.user.User;
import com.example.blog.repository.user.UserRepository;
import com.example.blog.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserResponseDto.JoinUser saveUser(UserRequestDto.JoinUser requestDto) throws Exception {
        User defaultUser = User.setDefaultUser(requestDto);
        userRepository.save(defaultUser);

        return null;
    }
}
