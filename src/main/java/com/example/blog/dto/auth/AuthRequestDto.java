package com.example.blog.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@ApiModel(description = "인증관련 요청 파라미터 관리")
public class AuthRequestDto {

    @Getter
    @Builder
    public static class GenerateUserToken {

        @ApiModelProperty(position = 1 ,  value = "유저 이메일 (ID)",required = true)
        @Email
        @NotEmpty(message = "사용자 이메일은 필수항목입니다.")
        private String userEmail;

        @ApiModelProperty(position = 2 ,  value = "패스워드",required = true)
        @NotEmpty(message = "사용자 비밀번호는 필수항목입니다.")
        private String password;

    }


    @Getter
    @Builder
    public static class GenerateGuestToken {

    }

}
