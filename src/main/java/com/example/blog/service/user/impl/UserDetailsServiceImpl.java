package com.example.blog.service.user.impl;

import com.example.blog.dto.user.UserDetailsDto;
import com.example.blog.dto.user.UserDto;
import com.example.blog.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        UserDto userDto = UserDto
                .builder()
                .userEmail(userEmail)
                .build();

        // 사용자 정보가 존재하지 않는 경우
        if (userEmail == null || userEmail.equals("")) {
            return userService.findUserByEmail(userDto)
                    .map(u -> new UserDetailsDto(u))
                    .orElseThrow(() -> new AuthenticationServiceException("Authentication failed : " + userEmail));
        }
        else {
            return userService.findUserByEmail(userDto)
                    .map(u -> new UserDetailsDto(u))
                    .orElseThrow(() -> new AuthenticationServiceException("Authentication failed : " + userEmail));
        }
    }
}