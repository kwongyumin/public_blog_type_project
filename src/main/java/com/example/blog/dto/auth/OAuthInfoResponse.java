package com.example.blog.dto.auth;

import com.example.blog.model.user.OAuthType;

/**
 *  Oauth 응답 공통
 */
public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    OAuthType getOAuthType();
}
