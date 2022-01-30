package com.example.authorizationserver.domain.oauth.exception;

import com.example.authorizationserver.global.error.ErrorCode;
import com.example.authorizationserver.global.error.exception.BJException;

public class InvalidOauthCodeException extends BJException {
    public static BJException EXCEPTION =
            new InvalidOauthCodeException();

    private InvalidOauthCodeException(){
        super(ErrorCode.INVALID_OAUTH_CODE);
    }
}
