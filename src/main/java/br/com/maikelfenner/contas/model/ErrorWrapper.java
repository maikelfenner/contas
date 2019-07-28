package br.com.maikelfenner.contas.model;

public class ErrorWrapper {

    private String error;

    public ErrorWrapper(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
