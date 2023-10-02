package com.picpaysimplificado.exception;

import com.picpaysimplificado.domain.user.UserType;

public class UserNotAllowedException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public UserNotAllowedException(UserType type) {
        super("Usuário do tipo " + type +" não está autorizado a realizar transações");
    }
}
