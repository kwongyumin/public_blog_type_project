package com.example.blog.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(description = "로그인 관련 응답 파라미터 관리")
public class UserResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinUser implements Serializable {

        @ApiModelProperty(position = 1 ,  value = "유저 idx", required = true, example = "15")
        private Long userId;
        // fixme : 회원가입 후 return URL 어떻게 관리할지 생각하기.
        @ApiModelProperty(position = 2 ,  value = "화면별 이동 URL 관리", required = true, example = "")
        private String returnUrl;
    }
}
