package com.mendix.recipes.common.exception;

public class ErrorOccurredException extends BaseException {

    public ErrorOccurredException(final String msgId) {
        super(msgId);
    }

    public ErrorOccurredException(final String msgId, final Exception ex) {
        super(msgId, ex);
    }
}
