package com.zgd.hw.exception;

/**
 * @author huangwei
 * @description
 * @create 2021-03-31-16:01
 */
public class AccessForbiddenException extends RuntimeException{
    static final long serialVersionUID = -70348971907459L;

    public AccessForbiddenException() {
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    public AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
