package com.example.blog.dto.auth;

import com.example.blog.model.user.OAuthType;
import org.springframework.util.MultiValueMap;

/**
 *  Oauth 요청 공통 (클라이언트 -> 서버)
 */
public interface OAuthRequestParams {

    OAuthType oAuthType();
    MultiValueMap<String, String> makeBody();
}
