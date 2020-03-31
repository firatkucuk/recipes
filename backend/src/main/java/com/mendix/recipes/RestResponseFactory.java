package com.mendix.recipes;

import java.util.Locale;
import java.util.UUID;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public final class RestResponseFactory {

    private final MessageSource messageSource;

    public RestResponseFactory(final MessageSource messageSource) {

        this.messageSource = messageSource;
    }

    public RestResponse<Void> error(final String msgId) {

        return createMessage("ERROR", msgId, null);
    }

    public <T> RestResponse<T> error(final String msgId, final T data) {

        return createMessage("ERROR", msgId, data);
    }

    public RestResponse<Void> info(final String msgId) {

        return createMessage("INFO", msgId, null);
    }

    public <T> RestResponse<T> info(final String msgId, final T data) {

        return createMessage("INFO", msgId, data);
    }

    public RestResponse<Void> success(final String msgId) {

        return createMessage("SUCCESS", msgId, null);
    }

    public <T> RestResponse<T> success(final String msgId, final T data) {

        return createMessage("SUCCESS", msgId, data);
    }

    public RestResponse<Void> warning(final String msgId) {

        return createMessage("WARNING", msgId, null);
    }

    public <T> RestResponse<T> warning(final String msgId, final T data) {

        return createMessage("WARNING", msgId, data);
    }

    private <T> RestResponse<T> createMessage(final String type, final String msgId, final T data) {

        final RestResponse<T> restResponse = new RestResponse<>();
        restResponse.setType(type);
        restResponse.setErrorCode(UUID.randomUUID().toString());
        restResponse.setMsgId(msgId);
        restResponse.setText(messageSource.getMessage(msgId, null, msgId, Locale.getDefault()));
        restResponse.setData(data);

        return restResponse;
    }
}
