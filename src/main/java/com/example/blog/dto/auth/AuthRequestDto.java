package com.example.blog.dto.auth;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;


@ApiModel(description = "인증관련 요청 파라미터 관리")
public class AuthRequestDto {


    @Getter
    @Builder
    public static class GuestToken {

    }

    @Getter
    @Builder
    public static class UserToken {

    }
}
