package com.example.blog.dto.user;


import com.example.blog.model.user.LoginType;
import lombok.Builder;
import lombok.Getter;


public class UserRequestDto {
    /*
        Note : inner class 관리
     */

    @Getter
    @Builder
    public static class LoginUser {

        private String email;

        private String password;

    }

    @Getter
    @Builder
    public static class JoinUser {

        private String userName;

        private String nickName;

        private String password;

        private String email;

        private String intro;
    }

}
