package com.hygorluciano.lojaderoupa.domain.exception;

public class LoginExecption extends RuntimeException{
    public LoginExecption() {
    }

    public LoginExecption(String message) {
        super(message);
    }

    public LoginExecption(String message, Throwable cause) {
        super(message, cause);
    }
}
