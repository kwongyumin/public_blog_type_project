package com.example.blog.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;


@ApiModel(description = "인증관련 요청 파라미터 관리")
public class AuthRequestDto {

    @Getter
    @Builder
    public static class GenerateUserToken {

        @ApiModelProperty(position = 1 ,  value = "유저 이메일 (ID)",required = true)
        @Email
        private String userEmail;
    }


    @Getter
    @Builder
    public static class GenerateGuestToken {

    }

}
