package org.example.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorModel {

    private int statusCode;

    private String exception;

    private String message;
}
