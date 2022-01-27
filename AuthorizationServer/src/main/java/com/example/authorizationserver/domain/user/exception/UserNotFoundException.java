package com.example.authorizationserver.domain.user.exception;

import com.example.authorizationserver.global.error.ErrorCode;
import com.example.authorizationserver.global.error.exception.BJException;

public class UserNotFoundException extends BJException {
    public static BJException EXCEPTION =
            new UserNotFoundException();

    private UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}
