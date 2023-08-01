package com.example.blog.service.user.impl;

import com.example.blog.dto.user.UserRequestDto;
import com.example.blog.repository.user.UserRepository;
import com.example.blog.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserService us, UserRepository up) {
        this.userService = us;
        this.userRepository = up;
    }


    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        // 입력받은 ID (email ) 패스워드
        UserRequestDto.LoginUser userDto = UserRequestDto.LoginUser.builder()
                .userEmail(userEmail)
                .build();

        // 사용자 정보 조회
        // FIXME : 이미 만들어진 비즈니스 로직을 사용해서 처리 ?


        return null;

    }
}