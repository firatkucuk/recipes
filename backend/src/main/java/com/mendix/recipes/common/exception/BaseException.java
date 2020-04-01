package com.mendix.recipes.common.exception;

public class BaseException extends RuntimeException {

    public BaseException(final String msgId) {

        super(msgId);
    }

    public BaseException(final String msgId, final Exception ex) {

        super(msgId, ex);
    }
}
