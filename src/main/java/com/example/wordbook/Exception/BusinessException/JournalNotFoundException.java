package com.example.wordbook.Exception.BusinessException;

import com.example.wordbook.Exception.ErrorCode;

public class JournalNotFoundException extends BusinessException {

    public JournalNotFoundException(String value) {
        super(value, ErrorCode.JOURNAL_NOT_FOUND);
    }

    public JournalNotFoundException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }
}
