package com.example.blog.service.user.impl;

import com.example.blog.dto.user.UserDetailsDto;
import com.example.blog.dto.user.UserDto;
import com.example.blog.dto.user.UserRequestDto;
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

    public UserDetailsServiceImpl(UserService us) {
        this.userService = us;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        UserRequestDto.LoginUser userDto = UserRequestDto.LoginUser.builder()
                .userEmail(userEmail)
                .build();

        // FIXME : 로그인만 optional 처리 하는 것이 맞는것인지? 전체적인 코드 컨벤선 생각해서 결과반환 다시 생각!\
        // 사용자 정보가 존재하지 않는 경우
        if (userEmail == null || userEmail.equals("")) {
            // FIXME : 조금 더 생각해보기 ...
//            return userService.loginUser(userDto)
//                    .map(u -> new UserDetailsDto(u, Collections.singleton(new SimpleGrantedAuthority(u.getUserId()))))
//                    .orElseThrow(() -> new AuthenticationServiceException(userEmail));
        }
        // 비밀번호가 맞지 않는 경우
        else {
//            return userService.loginUser(userDto)
//                    .map(u -> new UserDetailsDto(u, Collections.singleton(new SimpleGrantedAuthority(u.getUserId()))))
//                    .orElseThrow(() -> new BadCredentialsException(userEmail));

        }
        return null;
    }
}