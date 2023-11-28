package com.hygorluciano.lojaderoupa.domain.exception;

public class ValorExisterExecption extends RuntimeException{
    public ValorExisterExecption() {
        super();
    }

    public ValorExisterExecption(String message) {
        super(message);
    }

    public ValorExisterExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public ValorExisterExecption(Throwable cause) {
        super(cause);
    }

    protected ValorExisterExecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
