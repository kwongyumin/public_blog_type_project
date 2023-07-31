package com.example.blog.dto.user;


import lombok.Builder;
import lombok.Getter;


public class UserRequestDto {
    /*
        Note : inner class 관리
     */

    @Getter
    @Builder
    public static class LoginUser {
        // 사용자 시퀀스
        private Long userId;
        // 사용자 이름
        private String userName;
        // 사용자 아이디
        private String userEmail;
        // 사용자 패스워드
        private String userPassword;

    }

    @Getter
    @Builder
    public static class JoinUser {
        // 파라미터 필수 여부 체크 필요
        private String userName;

        private String nickName;

        private String email;

        private String password;

        private String intro;
    }

}
