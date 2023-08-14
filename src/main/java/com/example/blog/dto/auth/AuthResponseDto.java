package com.example.blog.dto.auth;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@ApiModel(description = "인증관련 응답 파라미터 관리")
public class AuthResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GenerateUserToken implements Serializable {

        @ApiModelProperty(position = 1 , value = "유저 pk", required = true)
        private Long userId;
        @ApiModelProperty(position = 2 , value = "접근 토큰", required = true)
        private String accessToken;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GenerateGuestToken implements Serializable {

        @ApiModelProperty(position = 1 , value = "게스트 pk", required = true)
        private Long guestId;
        @ApiModelProperty(position = 2 , value = "접근 토큰", required = true)
        private String accessToken;

    }

}
