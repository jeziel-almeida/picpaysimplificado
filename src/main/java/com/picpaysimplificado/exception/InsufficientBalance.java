package com.picpaysimplificado.exception;

public class InsufficientBalance extends Exception {
    
    private static final long serialVersionUID = 1L;

    public InsufficientBalance() {
        super("Saldo insuficiente");
    }
}
