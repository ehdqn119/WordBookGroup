package com.example.wordbook.Exception.BusinessException;


import com.example.wordbook.Exception.ErrorCode;

public class EntityValidationException extends RuntimeException {

    private ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;

    public EntityValidationException(String message) {
        super(message);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
