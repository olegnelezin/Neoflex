package org.example.advice;

import org.example.dto.ErrorMessage;
import org.example.exception.InternalException;
import org.example.utli.MessageHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@RestControllerAdvice
public class InternalExceptionHandler extends BaseControllerAdvice {

    public InternalExceptionHandler(MessageHelper messageHelper) {
        super(messageHelper);
    }

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<ErrorMessage> handleException(InternalException exception) {
        HttpStatus status = null;
        switch (exception.getCode()) {
            case INTERNAL_ERROR:
                status = INTERNAL_SERVER_ERROR;
                break;
            case SERVICE_UNAVAILABLE:
                status = SERVICE_UNAVAILABLE;
                break;
        }

        var code = exception.getCode().toString();
        var userMessageProperty = exception.getCode().getUserMessageProperty();
        return formErrorMessage(exception, status, code, userMessageProperty);
    }
}
