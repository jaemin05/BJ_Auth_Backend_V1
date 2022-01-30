package com.example.authorizationserver.domain.oauth.exception;

import com.example.authorizationserver.global.error.ErrorCode;
import com.example.authorizationserver.global.error.exception.BJException;
import org.springframework.security.core.parameters.P;

public class InvalidClientSecretException extends BJException {
    public static BJException EXCEPTION =
            new InvalidClientSecretException();

    private InvalidClientSecretException(){
        super(ErrorCode.INVALID_CLIENT_SECRET);
    }
}
