package com.example.blog.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserResponseDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JoinUser {

        private Long userId;
        // fixme : 회원가입 후 return URL 어떻게 관리할지 생각하기.
        private String returnUrl;
    }
}
