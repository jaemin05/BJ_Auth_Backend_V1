package com.example.resourceserver.global.error.exception;

import com.example.resourceserver.global.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BJException extends RuntimeException{
    private final ErrorCode errorCode;
}
