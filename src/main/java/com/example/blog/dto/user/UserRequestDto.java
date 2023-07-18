package com.example.blog.dto.user;


import com.example.blog.model.user.LoginType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestDto {

    /*
        Note : inner class 관리
     */
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JoinUser {

        private String userName;

        private String nickName;

        private String password;

        private String email;

        private String intro;

        private LoginType loginType;
    }

}
