package com.example.authorizationserver.domain.oauth.api;

import com.example.authorizationserver.domain.oauth.api.dto.ClientDto;
import com.example.authorizationserver.domain.oauth.api.dto.request.RegisterClientRequest;
import com.example.authorizationserver.domain.oauth.service.OauthService;
import com.example.authorizationserver.domain.user.api.dto.request.LoginRequest;
import com.example.authorizationserver.global.security.jwt.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class OauthController {
    private final OauthService oauthService;

    @PostMapping("/client")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto registerClient(@RequestBody @Valid RegisterClientRequest request){
        return oauthService.registerClient(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.FOUND)
    public String login(@RequestParam(name = "client_id")String clientId,
                        @RequestParam(name = "redirect_uri")String redirectUri,
                        @RequestBody @Valid LoginRequest loginRequest) {
        String code = oauthService.login(loginRequest, clientId, redirectUri);
        return "redirect:" + redirectUri + "?code=" + code;
    }

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse getToken(@RequestParam String code,
                                  @RequestBody ClientDto clientDto) {
        return oauthService.getToken(code, clientDto);
    }
}
