package com.picpaysimplificado.exception;

public class InsufficientBalanceException extends Exception {
    
    private static final long serialVersionUID = 1L;

    public InsufficientBalanceException() {
        super("Saldo insuficiente");
    }
}
