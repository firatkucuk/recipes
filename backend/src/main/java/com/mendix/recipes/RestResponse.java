package com.mendix.recipes;

import lombok.Getter;
import lombok.Setter;

@Getter
public final class RestResponse<T> {

    private final ResponseType type;
    private final String       msgId;

    @Setter
    private T      data;
    @Setter
    private String errorCode;
    @Setter
    private String text;

    public RestResponse(final ResponseType type, final String msgId) {

        this.type = type;
        this.msgId = msgId;
    }
}
