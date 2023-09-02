package com.example.blog.service.auth.impl;

import com.example.blog.dto.auth.OAuthInfoResponse;
import com.example.blog.dto.auth.OAuthRequestParams;
import com.example.blog.model.user.OAuthType;
import com.example.blog.service.auth.OAuthApiClient;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RequestOAuthInfo {

    private final Map<OAuthType, OAuthApiClient> clients;

    public RequestOAuthInfo(List<OAuthApiClient> clients) {
        this.clients = clients.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toMap(OAuthApiClient::oAuthType, Function.identity()),
                        Collections::unmodifiableMap )
        );
    }

    public OAuthInfoResponse request(OAuthRequestParams params) {
        OAuthApiClient client = clients.get(params.oAuthType());
        String accessToken = client.requestAccessToken(params);
        return client.requestOauthInfo(accessToken);
    }
}
