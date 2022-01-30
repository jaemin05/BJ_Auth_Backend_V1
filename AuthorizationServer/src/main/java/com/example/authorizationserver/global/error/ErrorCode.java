package com.example.authorizationserver.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(404, "User Not Found"),
    USER_ALREADY_EXISTS(409, "User Already Exists"),

    EXPIRED_TOKEN(401, "Expired Token"),
    INVALID_TOKEN(401, "Invalid Token"),

    CLIENT_NOT_FOUND(404, "Client Not Found"),
    INVALID_CLIENT_SECRET(401, "Invalid Client Secret"),
    INVALID_OAUTH_CODE(401, "Invalid Oauth Code");

    private final int status;
    private final String message;
}
