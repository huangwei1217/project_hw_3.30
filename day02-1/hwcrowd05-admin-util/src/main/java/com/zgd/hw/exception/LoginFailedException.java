package com.zgd.hw.exception;

/**
 * @author huangwei
 * @description
 * @create 2021-03-31-13:38
 */
public class LoginFailedException extends RuntimeException{
    static final long serialVersionUID = -7034897190745766931L;

    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    public LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
