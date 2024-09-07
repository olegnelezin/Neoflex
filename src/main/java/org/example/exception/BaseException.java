package org.example.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class BaseException extends RuntimeException {

    protected Map<String, Object> args;

    protected String messageParam;

    protected BaseException(String msg, Throwable e) {
        super(msg, e);
    }

    public BaseException messageParam(String messageParam) {
        this.messageParam = messageParam;
        return this;
    }

    public String getArgsAsString() {
        if (this.args == null) {
            return "";
        }

        return args.toString();
    }
}
