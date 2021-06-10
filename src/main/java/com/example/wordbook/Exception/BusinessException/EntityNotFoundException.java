package com.example.wordbook.Exception.BusinessException;

import com.example.wordbook.Exception.ErrorCode;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String message) {
        super(message, ErrorCode.USER_NOT_FOUND);
    }
}




