package com.example.authorizationserver.domain.oauth.service;

import com.example.authorizationserver.domain.oauth.api.dto.ClientDto;
import com.example.authorizationserver.domain.oauth.api.dto.request.RegisterClientRequest;
import com.example.authorizationserver.domain.user.api.dto.request.LoginRequest;
import com.example.authorizationserver.global.security.jwt.dto.response.TokenResponse;

public interface OauthService {
    ClientDto registerClient(RegisterClientRequest request);
    String login(LoginRequest loginRequest, String clientId, String redirectUri);
    TokenResponse getToken(String code, ClientDto clientDto);
}
