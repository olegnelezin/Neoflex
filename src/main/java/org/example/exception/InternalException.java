package org.example.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class InternalException extends BaseException {

    protected final Code code;

    protected InternalException(Code code, Throwable e, String msg) {
        super(msg, e);
        this.code = code;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Code {

        INTERNAL_ERROR("error-message.internal-error"),
        SERVICE_UNAVAILABLE("error-message.service_unavailable"),
        ;

        private final String userMessageProperty;

        public InternalException get(String msg) {
            return new InternalException(this, null, msg);
        }

        public InternalException get(Throwable e, String msg) {
            return new InternalException(this, e, msg);
        }
    }
}
