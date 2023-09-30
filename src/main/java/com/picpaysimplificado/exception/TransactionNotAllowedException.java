package com.picpaysimplificado.exception;

import com.picpaysimplificado.domain.user.UserType;

public class TransactionNotAllowedException extends Exception {
    
    private static final long serialVersionUID = 1L;

    public TransactionNotAllowedException(UserType type) {
        super("Usuário do tipo " + type +" não está autorizado a realizar transações");
    }
}
