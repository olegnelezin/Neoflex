package org.example.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ErrorMessage;
import org.example.exception.BaseException;
import org.example.utli.MessageHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Slf4j
@RequiredArgsConstructor
public class BaseControllerAdvice {

    private final MessageHelper messageHelper;

    protected ResponseEntity<ErrorMessage> formErrorMessage(BaseException exception, HttpStatus status,
                                                            String code, String userMessageProperty) {
        var userMessage = messageHelper.getMessage(userMessageProperty);
        if (userMessage != null && exception.getMessageParam() != null) {
            userMessage = String.format(userMessage, exception.getMessageParam());
        }
        var devMessage = exception.getMessage();
        var args = exception.getArgs();
        log.error(code + " : " + exception.getArgsAsString(), exception);
        return ResponseEntity
                .status(status)
                .body(ErrorMessage.builder()
                        .code(code)
                        .userMessage(userMessage)
                        .devMessage(devMessage)
                        .args(args)
                        .build());
    }
}
