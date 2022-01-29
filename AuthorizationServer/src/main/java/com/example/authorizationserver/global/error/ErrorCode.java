package com.example.authorizationserver.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(404, "User Not Found"),
    USER_ALREADY_EXISTS(409, "User Already Exists"),

    EXPIRED_TOKEN(401, "Expired Token"),
    INVALID_TOKEN(401, "Invalid Token");

    private final int status;
    private final String message;
}
