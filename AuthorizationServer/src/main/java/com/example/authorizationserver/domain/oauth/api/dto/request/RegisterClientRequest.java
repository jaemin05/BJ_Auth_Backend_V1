package com.example.authorizationserver.domain.oauth.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class RegisterClientRequest {
    @NotBlank(message = "redirectUri는 공백을 허용하지 않습니다.")
    private String redirectUri;
}
