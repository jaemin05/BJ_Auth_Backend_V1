package com.example.authorizationserver.global.exception;

import com.example.authorizationserver.global.error.ErrorCode;
import com.example.authorizationserver.global.error.exception.BJException;

public class InvalidTokenException extends BJException {
    public static BJException EXCEPTION =
            new InvalidTokenException();

    private InvalidTokenException(){
        super(ErrorCode.INVALID_TOKEN);
    }
}
