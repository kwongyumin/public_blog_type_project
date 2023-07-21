package com.example.blog.service.user.impl;

import com.example.blog.dto.user.UserDto;
import com.example.blog.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService us) {
        this.userService = us;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) {
        UserDto userDto = UserDto
                .builder()
                .userId(Long.parseLong(userId))
                .build();

        // 사용자 정보가 존재하지 않는 경우
//        if (userId == null || userId.equals("")) {
//            return userService.login(userDto)
//                    .map(u -> new UserDetailsDto(u, Collections.singleton(new SimpleGrantedAuthority(u.getUserId()))))
//                    .orElseThrow(() -> new AuthenticationServiceException(userId));
//        }
//        // 비밀번호가 맞지 않는 경우
//        else {
//            return userService.login(userDto)
//                    .map(u -> new UserDetailsDto(u, Collections.singleton(new SimpleGrantedAuthority(u.getUserId()))))
//                    .orElseThrow(() -> new BadCredentialsException(userId));
//        }
        return null;
    }

}