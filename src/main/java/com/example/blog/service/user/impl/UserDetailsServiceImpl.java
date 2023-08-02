package com.example.blog.service.user.impl;

import com.example.blog.dto.user.UserDetailsDto;
import com.example.blog.dto.user.UserDto;
import com.example.blog.repository.user.UserRepository;
import com.example.blog.service.user.UserService;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService us, UserRepository up) {
        this.userService = us;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        UserDto userDto = UserDto
                .builder()
                .userEmail(userEmail)
                .build();

        // 사용자 정보가 존재하지 않는 경우
        if (userEmail == null || userEmail.equals("")) {
            return userService.loginUser(userDto)
                    .map(u -> new UserDetailsDto(u, Collections.singleton(new SimpleGrantedAuthority(u.getUserEmail()))))
                    .orElseThrow(() -> new AuthenticationServiceException(userEmail));
            // SimpleGrantedAuthority Role 처리 생각해보기.
        }
        // 비밀번호가 맞지 않는 경우
        else {
            return userService.loginUser(userDto)
                    .map(u -> new UserDetailsDto(u, Collections.singleton(new SimpleGrantedAuthority(u.getUserEmail()))))
                    .orElseThrow(() -> new BadCredentialsException(userEmail));
        }
    }
}