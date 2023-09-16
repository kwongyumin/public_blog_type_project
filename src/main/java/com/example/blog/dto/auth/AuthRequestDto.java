package com.example.blog.dto.auth;

import com.example.blog.model.user.OAuthType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@ApiModel(description = "인증관련 요청 파라미터 관리")
public class AuthRequestDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GenerateUserToken {

        @ApiModelProperty(position = 1 ,  value = "유저 이메일 (ID)",required = true)
        @NotEmpty(message = "사용자 이메일은 필수 항목 입니다.")
        @Email
        private String userEmail;

        @ApiModelProperty(position = 2 ,  value = "패스워드",required = true)
        @NotEmpty(message = "사용자 비밀번호는 필수 항목 입니다.")
        private String password;

    }


//    @Getter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Builder
//    public static class GenerateGuestToken {
//
//    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GenerateUserTokenFromKakao implements OAuthRequestParams {

        @ApiModelProperty(position = 1 ,  value = "카카오톡 인증 코드 ",required = true)
        @NotEmpty(message = "카카오톡 인증코드는 필수 항목 입니다.")
        private String authorizationCode;

        @Override
        public OAuthType oAuthType() {
            return OAuthType.KAKAO;
        }

        @Override
        public MultiValueMap<String, String> makeBody() {
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("code", authorizationCode);
            return body;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GenerateUserTokenFromNaver implements OAuthRequestParams {

        @ApiModelProperty(position = 1 ,  value = "네이버 인증 코드",required = true)
        @NotEmpty(message = "네이버 인증코드는 필수 항목 입니다.")
        private String authorizationCode;

        @ApiModelProperty(position = 2 ,  value = "상태값",required = true)
        @NotEmpty(message = "상태값은 필수 항목 입니다.")
        private String state;

        @Override
        public OAuthType oAuthType() {
            return OAuthType.NAVER;
        }

        @Override
        public MultiValueMap<String, String> makeBody() {
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("code", authorizationCode);
            body.add("state", state);
            return body;
        }
    }

}
