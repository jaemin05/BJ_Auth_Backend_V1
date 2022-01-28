package com.example.authorizationserver.global.exception;

import com.example.authorizationserver.global.error.ErrorCode;
import com.example.authorizationserver.global.error.exception.BJException;

public class ExpiredTokenException extends BJException {
    public static BJException EXCEPTION =
            new ExpiredTokenException();

    private ExpiredTokenException(){
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
