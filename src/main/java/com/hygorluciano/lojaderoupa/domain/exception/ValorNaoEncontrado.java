package com.hygorluciano.lojaderoupa.domain.exception;

public class ValorNaoEncontrado extends RuntimeException{
    public ValorNaoEncontrado() {
        super();
    }

    public ValorNaoEncontrado(String message) {
        super(message);
    }

    public ValorNaoEncontrado(String message, Throwable cause) {
        super(message, cause);
    }

    public ValorNaoEncontrado(Throwable cause) {
        super(cause);
    }

    protected ValorNaoEncontrado(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
