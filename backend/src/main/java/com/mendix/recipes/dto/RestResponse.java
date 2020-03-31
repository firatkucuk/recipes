package com.mendix.recipes.dto;

import lombok.Data;

@Data
public final class RestResponse<T> {

    private T      data;
    private String msgId;
    private String errorCode;
    private String text;
    private String type;
}
