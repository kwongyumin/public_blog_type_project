package com.example.blog.dto.user;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@AllArgsConstructor
public class UserDetailsDto implements UserDetails {

    @Delegate
    private UserDto userDto;

    // FIXME: 권한 처리방식 수정
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userDto.getAuthorityList().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userDto.getUserPassword();
    }

    // FIXME : 반환 principal 을 아이디 값인 이메일로 사용하는 것이 맞지않을까? , 전체적인 로직 흐름 판단 후 확인 필요
    @Override
    public String getUsername() {
        return userDto.getUserEmail();
    }

    // FIXME : Jwt 인증 사용을 위해 true 변환 ->
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}