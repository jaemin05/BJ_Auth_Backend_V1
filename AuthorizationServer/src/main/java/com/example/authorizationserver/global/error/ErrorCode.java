package com.example.authorizationserver.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(404, "User Not Found");

    private final int status;
    private final String message;
}
