package com.example.blog.dto.auth;

import com.example.blog.model.user.OAuthType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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


    @Getter
    @NoArgsConstructor
    public static class KakaoTokens {

        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("token_type")
        private String tokenType;

        @JsonProperty("refresh_token")
        private String refreshToken;

        @JsonProperty("expires_in")
        private String expiresIn;

        @JsonProperty("refresh_token_expires_in")
        private String refreshTokenExpiresIn;

        @JsonProperty("scope")
        private String scope;

    }

    @Getter
    @NoArgsConstructor
    public static class NaverTokens {

        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("refresh_token")
        private String refreshToken;

        @JsonProperty("token_type")
        private String tokenType;

        @JsonProperty("expires_in")
        private String expiresIn;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KakaoInfoResponse implements OAuthInfoResponse {

        @JsonProperty("kakao_account")
        private KakaoAccount kakaoAccount;

        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        static class KakaoAccount {
            private KakaoProfile profile;
            private String email;
        }

        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        static class KakaoProfile {
            private String nickname;
        }

        @Override
        public String getEmail() {
            return kakaoAccount.email;
        }

        @Override
        public String getNickname() {
            return kakaoAccount.profile.nickname;
        }

        @Override
        public OAuthType getOAuthType() {
            return OAuthType.KAKAO;
        }

    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NaverInfoResponse implements OAuthInfoResponse {

        @JsonProperty("response")
        private Response response;

        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        static class Response {
            private String email;
            private String nickname;
        }

        @Override
        public String getEmail() {
            return response.email;
        }

        @Override
        public String getNickname() {
            return response.nickname;
        }

        @Override
        public OAuthType getOAuthType() {
            return OAuthType.NAVER;
        }
    }

}
