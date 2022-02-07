package com.donus.donusbackendchallenge.config.validation;

public class ValidationException extends Exception {
    private String campo;

    public ValidationException(String campo, String errorMessage) {
        super(errorMessage);
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }

}