package com.example.wordbook.Exception.BusinessException;

import com.example.wordbook.Exception.ErrorCode;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(String value) {
        super(value, ErrorCode.USER_NOT_FOUND);
    }

    public UserNotFoundException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }
}
