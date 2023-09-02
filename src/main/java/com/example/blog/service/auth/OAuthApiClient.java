package com.example.blog.service.auth;

import com.example.blog.dto.auth.OAuthInfoResponse;
import com.example.blog.dto.auth.OAuthRequestParams;
import com.example.blog.model.user.OAuthType;


/**
 *  Oauth api 요청 공통 (서버 -> OAuth 인증 서버)
 */
public interface OAuthApiClient {

    OAuthType oAuthType();
    String requestAccessToken(OAuthRequestParams params);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}
