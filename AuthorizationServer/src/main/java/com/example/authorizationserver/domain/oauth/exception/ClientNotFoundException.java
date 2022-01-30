package com.example.authorizationserver.domain.oauth.exception;

import com.example.authorizationserver.global.error.ErrorCode;
import com.example.authorizationserver.global.error.exception.BJException;

public class ClientNotFoundException extends BJException {
    public static BJException EXCEPTION =
            new ClientNotFoundException();

    private ClientNotFoundException(){
        super(ErrorCode.CLIENT_NOT_FOUND);
    }
}
