package com.example.authorizationserver.domain.oauth.service;

import com.example.authorizationserver.domain.oauth.api.dto.ClientDto;
import com.example.authorizationserver.domain.oauth.api.dto.request.RegisterClientRequest;
import com.example.authorizationserver.domain.oauth.entity.OauthCode;
import com.example.authorizationserver.domain.oauth.entity.OauthDetails;
import com.example.authorizationserver.domain.oauth.exception.ClientNotFoundException;
import com.example.authorizationserver.domain.oauth.exception.InvalidClientSecretException;
import com.example.authorizationserver.domain.oauth.exception.InvalidOauthCodeException;
import com.example.authorizationserver.domain.oauth.facade.OauthFacade;
import com.example.authorizationserver.domain.oauth.repository.OauthDetailsRepository;
import com.example.authorizationserver.domain.refreshtoken.entiry.RefreshToken;
import com.example.authorizationserver.domain.refreshtoken.repository.RefreshTokenRepository;
import com.example.authorizationserver.domain.user.api.dto.request.LoginRequest;
import com.example.authorizationserver.domain.user.entity.User;
import com.example.authorizationserver.domain.user.facade.UserFacade;
import com.example.authorizationserver.global.security.jwt.JwtTokenProvider;
import com.example.authorizationserver.global.security.jwt.dto.response.TokenResponse;
import com.example.authorizationserver.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService{
    private final AuthUtil authUtil;
    private final OauthFacade oauthFacade;
    private final UserFacade userFacade;
    private final JwtTokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OauthDetailsRepository oauthDetailsRepository;

    @Transactional
    @Override
    public ClientDto registerClient(RegisterClientRequest request) {
        ClientDto response = oauthFacade.getClientDetails();

        oauthDetailsRepository.save(
                new OauthDetails(
                        response.getClientId(),
                        response.getClientSecret(),
                        request.getRedirectUri()
                )
        );
        return response;
    }

    @Transactional
    @Override
    public String login(LoginRequest loginRequest, String clientId, String redirectUri){
        User user = userFacade.getByUsername(loginRequest.getUsername());
        OauthDetails oauthDetails = oauthFacade.getDetailsByClientId(clientId);

        if(!redirectUri.equals(oauthDetails.getRedirectUri())){
            throw ClientNotFoundException.EXCEPTION;
        }

        String code = authUtil.getRandomCode(7);
        oauthFacade.newOauthCode(clientId, code, loginRequest.getUsername());

        return code;
    }

    @Transactional
    @Override
    public TokenResponse getToken(String code, ClientDto clientDto) {
        String clientId = clientDto.getClientId();
        OauthDetails oauthDetails = oauthFacade.getDetailsByClientId(clientId);
        OauthCode oauthCode = oauthFacade.getCodeByClientId(clientId);

        if(!oauthDetails.getClientSecret().equals(clientDto.getClientSecret())){
            throw InvalidClientSecretException.EXCEPTION;
        }

        if(!oauthCode.getCode().equals(code)){
            throw InvalidOauthCodeException.EXCEPTION;
        }

        TokenResponse response = tokenProvider.generateToken(oauthCode.getUsername());
        refreshTokenRepository.save(
                new RefreshToken(
                        clientId, response.getRefreshToken()
                )
        );

        return response;
    }
}
