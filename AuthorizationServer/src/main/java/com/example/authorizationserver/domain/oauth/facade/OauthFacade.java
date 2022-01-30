package com.example.authorizationserver.domain.oauth.facade;

import com.example.authorizationserver.domain.oauth.api.dto.ClientDto;
import com.example.authorizationserver.domain.oauth.entity.OauthCode;
import com.example.authorizationserver.domain.oauth.entity.OauthDetails;
import com.example.authorizationserver.domain.oauth.exception.ClientNotFoundException;
import com.example.authorizationserver.domain.oauth.repository.OauthCodeRepository;
import com.example.authorizationserver.domain.oauth.repository.OauthDetailsRepository;
import com.example.authorizationserver.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OauthFacade {
    private final AuthUtil authUtil;
    private final OauthCodeRepository oauthCodeRepository;
    private final OauthDetailsRepository oauthDetailsRepository;

    public ClientDto getClientDetails(){
        String clientId = authUtil.getRandomCode(11);
        String clientSecret = authUtil.getRandomCode(11);
        return new ClientDto(clientId, clientSecret);
    }

    public OauthDetails getDetailsByClientId(String clientId){
        return oauthDetailsRepository.findById(clientId)
                .orElseThrow(() -> ClientNotFoundException.EXCEPTION);
    }

    public OauthCode getCodeByClientId(String clientId){
        return oauthCodeRepository.findById(clientId)
                .orElseThrow(() -> ClientNotFoundException.EXCEPTION);
    }

    public void newOauthCode(String clientId, String code, String username){
        oauthCodeRepository.save(
                new OauthCode(
                        clientId, code, username
                )
        );
    }
}
