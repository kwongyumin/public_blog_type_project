package com.example.blog.dto.user;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel(description = "로그인 관련 요청 파라미터 관리")
public class UserRequestDto {

    @Getter
    @Builder
    public static class JoinUser {

        @ApiModelProperty(position = 1 ,  value = "사용자 명", required = true, example = "")
        private String userName;

        @ApiModelProperty(position = 2 ,  value = "사용자 서비스 닉네임", required = true, example = "")
        private String nickName;

        @ApiModelProperty(position = 3 ,  value = "사용자 이메일 (ID)", required = true, example = "")
        private String email;

        @ApiModelProperty(position = 4 ,  value = "패스워드", required = true, example = "")
        private String password;

        @ApiModelProperty(position = 5 ,  value = "사용자 간단 소개", required = true, example = "")
        private String intro;
    }

}
