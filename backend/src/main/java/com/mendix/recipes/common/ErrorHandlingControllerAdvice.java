package com.mendix.recipes.common;

import com.mendix.recipes.common.exception.AccessNotAllowedException;
import com.mendix.recipes.common.exception.BadRequestException;
import com.mendix.recipes.common.exception.ErrorOccurredException;
import com.mendix.recipes.common.exception.ItemNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    private final RestResponseFactory resp;

    ErrorHandlingControllerAdvice(final RestResponseFactory resp) {

        this.resp = resp;
    }

    @ExceptionHandler(Throwable.class)
    ResponseEntity<RestResponse<Void>> defaultExceptionHandler(
        final Throwable ex,
        final HttpServletRequest request,
        final HttpServletResponse response) {

        if (response == null) {
            log.debug("An exception occurred", ex);

            return null;
        }

        if (ex instanceof HttpClientErrorException) {
            var ex2 = (HttpClientErrorException) ex;

            log.error(ex2.getResponseBodyAsString());
        }

        // Overriding response type
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp.error("errorOccurred"));
    }

    @ExceptionHandler(AccessNotAllowedException.class)
    ResponseEntity<RestResponse<Void>> handleException(
        final AccessNotAllowedException ex,
        final HttpServletResponse response
    ) {

        if (response == null) {
            log.debug("Access not allowed", ex);

            return null;
        }

        // Overriding response type
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(resp.error(ex.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<RestResponse<Void>> handleException(
        final BadRequestException ex,
        final HttpServletRequest request,
        final HttpServletResponse response) {

        if (response == null) {
            log.debug("Bad request", ex);

            return null;
        }

        // Overriding response type
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        return ResponseEntity.badRequest().body(resp.error(ex.getMessage()));
    }

    @ExceptionHandler(ErrorOccurredException.class)
    ResponseEntity<RestResponse<Void>> handleException(
        final ErrorOccurredException ex,
        final HttpServletRequest request,
        final HttpServletResponse response) {

        if (response == null) {
            log.debug("An error occurred", ex);

            return null;
        }

        // Overriding response type
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp.error(ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<RestResponse<String>> handleException(
        final HttpMessageNotReadableException ex,
        final HttpServletRequest request,
        final HttpServletResponse response) {

        if (response == null) {
            log.debug("Bad request", ex);

            return null;
        }

        // Overriding response type
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        return new ResponseEntity<>(resp.error("badRequest", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    ResponseEntity<RestResponse<Void>> handleException(
        final HttpRequestMethodNotSupportedException ex,
        final HttpServletRequest request,
        final HttpServletResponse response) {

        if (response == null) {
            log.debug("Wrong request method", ex);

            return null;
        }

        // Overriding response type
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        return new ResponseEntity<>(resp.error("methodNotSupported"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    ResponseEntity<RestResponse<Void>> handleException(
        final HttpMediaTypeNotAcceptableException ex,
        final HttpServletRequest request,
        final HttpServletResponse response) {

        if (response == null) {
            log.debug("Wrong mime type", ex);

            return null;
        }

        // Overriding response type
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        return new ResponseEntity<>(resp.error("methodNotSupported"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    ResponseEntity<RestResponse<String>> handleException(
        final ItemNotFoundException ex,
        final HttpServletRequest request,
        final HttpServletResponse response) {

        if (response == null) {
            log.debug("Item not found", ex);

            return null;
        }

        // Overriding response type
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        final String exceptionMessage = ex.getMessage();
        final String messageText      = exceptionMessage != null ? exceptionMessage : "itemNotFound";

        return new ResponseEntity<>(resp.error(messageText, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<RestResponse<Void>> handleException(
        final MethodArgumentNotValidException ex,
        final HttpServletRequest request,
        final HttpServletResponse response) {

        if (response == null) {
            log.debug("Invalid method argument", ex);

            return null;
        }

        // Overriding response type
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        return new ResponseEntity<>(resp.error("validationError"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    ResponseEntity<RestResponse<String>> handleException(
        final MissingServletRequestParameterException ex,
        final HttpServletRequest request,
        final HttpServletResponse response) {

        if (response == null) {
            log.debug("Invalid method argument", ex);

            return null;
        }

        // Overriding response type
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        return new ResponseEntity<>(resp.error("validationError", ex.getParameterName()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Returns form validation errors from controller validations
     *
     * @param ex Thrown validation exception
     * @return REST response
     */
    @ExceptionHandler(ValidationException.class)
    ResponseEntity<RestResponse<String>> handleException(
        final ValidationException ex,
        final HttpServletRequest request,
        final HttpServletResponse response) {

        if (response == null) {
            log.debug("Invalid request", ex);

            return null;
        }

        // Overriding response type
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        return new ResponseEntity<>(resp.error("validationError", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
