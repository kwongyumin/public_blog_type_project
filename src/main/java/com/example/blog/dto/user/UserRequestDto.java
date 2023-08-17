package com.example.blog.dto.user;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(description = "로그인 관련 요청 파라미터 관리")
public class UserRequestDto {

    @Getter
    @Builder
    public static class JoinUser {

        @ApiModelProperty(position = 1 ,  value = "사용자 명", required = true, example = "")
        @NotEmpty(message = "사용자명은 필수항목입니다.")
        private String userName;

        @ApiModelProperty(position = 2 ,  value = "사용자 서비스 닉네임", required = false, example = "")
        private String nickName;

        @ApiModelProperty(position = 3 ,  value = "사용자 이메일 (ID)", required = true, example = "")
        @Email
        @NotEmpty(message = "사용자 이메일(ID) 은 필수항목입니다.")
        private String userEmail;

        @ApiModelProperty(position = 4 ,  value = "패스워드", required = true, example = "")
        @NotEmpty(message = "패스워드는 필수항목입니다.")
        private String userPassword;

        @ApiModelProperty(position = 5 ,  value = "사용자 간단 소개", required = false, example = "")
        private String intro;

//        FIXME : 우선 USER , GUEST 테스트 후 어드민 추가 예정 , 현재 기준으로는 가입은 USER 권한을 가짐.
//        @ApiModelProperty(position = 6 ,  value = "사용자 권한", required = true, example = "USER")
//        private AuthType role;
    }

}
