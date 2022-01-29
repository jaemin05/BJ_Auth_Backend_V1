package com.example.authorizationserver.domain.user.exception;

import com.example.authorizationserver.global.error.ErrorCode;
import com.example.authorizationserver.global.error.exception.BJException;

public class UserAlreadyExistsException extends BJException {
    public static BJException EXCEPTION =
            new UserAlreadyExistsException();

    private UserAlreadyExistsException(){
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}
