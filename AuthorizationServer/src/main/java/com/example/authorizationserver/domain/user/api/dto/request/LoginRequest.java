package com.example.authorizationserver.domain.user.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "username은 공백을 허용하지 않습니다.")
    private String username;

    @NotBlank(message = "password는 공백을 허용하지 않습니다.")
    private String password;
}
