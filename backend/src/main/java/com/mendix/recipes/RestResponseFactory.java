package com.mendix.recipes;

import java.util.Locale;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class RestResponseFactory {

    private final MessageSource messageSource;

    public RestResponse<Void> error(final String msgId) {

        return createMessage(ResponseType.ERROR, msgId, null);
    }

    public <T> RestResponse<T> error(final String msgId, final T data) {

        return createMessage(ResponseType.ERROR, msgId, data);
    }

    public RestResponse<Void> info(final String msgId) {

        return createMessage(ResponseType.INFO, msgId, null);
    }

    public <T> RestResponse<T> info(final String msgId, final T data) {

        return createMessage(ResponseType.INFO, msgId, data);
    }

    public RestResponse<Void> success(final String msgId) {

        return createMessage(ResponseType.SUCCESS, msgId, null);
    }

    public <T> RestResponse<T> success(final String msgId, final T data) {

        return createMessage(ResponseType.SUCCESS, msgId, data);
    }

    public RestResponse<Void> warning(final String msgId) {

        return createMessage(ResponseType.WARNING, msgId, null);
    }

    public <T> RestResponse<T> warning(final String msgId, final T data) {

        return createMessage(ResponseType.WARNING, msgId, data);
    }

    private <T> RestResponse<T> createMessage(final ResponseType type, final String msgId, final T data) {

        final RestResponse<T> restResponse = new RestResponse<>(type, msgId);
        restResponse.setErrorCode(UUID.randomUUID().toString());
        restResponse.setText(messageSource.getMessage(msgId, null, msgId, Locale.getDefault()));
        restResponse.setData(data);

        return restResponse;
    }
}
