package org.example.advice;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final String GLOBAL_ERROR_CODE = "GLOBAL_ERROR";

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorMessage> handleException(HttpRequestMethodNotSupportedException ex) {
        var args = new HashMap<String, Object>();
        args.put(ex.getLocalizedMessage(), "Invalid request method type");

        var errorMessage = setErrorMessage(args);

        var response = setResponse(errorMessage, HttpStatus.METHOD_NOT_ALLOWED);
        log.error(ex.getMessage(), ex);
        return response;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> handleException(MethodArgumentTypeMismatchException ex) {
        var args = new HashMap<String, Object>();
        args.put(ex.getLocalizedMessage(), "Incorrect argument type");

        var errorMessage = setErrorMessage(args);

        var response = setResponse(errorMessage, HttpStatus.BAD_REQUEST);
        log.error(ex.getMessage(), ex);
        return response;
    }

    private ErrorMessage setErrorMessage(HashMap<String, Object> args) {
        return ErrorMessage.builder()
                .code(GLOBAL_ERROR_CODE)
                .args(args)
                .build();
    }

    private ResponseEntity<ErrorMessage> setResponse(ErrorMessage errorMessage, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(errorMessage);
    }
}
