package io.github.ferraznt.exception;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException() {
        super("Senha Inválida!");
    }
}
