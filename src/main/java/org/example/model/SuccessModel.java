package org.example.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SuccessModel<T> {

    private int statusCode;

    private String subject;

    private T data;

    public static <T> SuccessModel<T> okSuccessModel(T data, String subject) {
        return new SuccessModel<>(200, subject, data);
    }
}
