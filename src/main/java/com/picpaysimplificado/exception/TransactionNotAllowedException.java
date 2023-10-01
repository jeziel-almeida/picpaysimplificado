package com.picpaysimplificado.exception;

public class TransactionNotAllowedException extends Exception {
    
    private static final long serialVersionUID = 1L;

    public TransactionNotAllowedException() {
        super("Transação não autorizada");
    }
}
