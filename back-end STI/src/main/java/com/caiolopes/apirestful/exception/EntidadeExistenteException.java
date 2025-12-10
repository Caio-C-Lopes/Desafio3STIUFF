package com.caiolopes.apirestful.exception;

public class EntidadeExistenteException extends RuntimeException {
    public EntidadeExistenteException(String message) {
        super(message);
    }
}
