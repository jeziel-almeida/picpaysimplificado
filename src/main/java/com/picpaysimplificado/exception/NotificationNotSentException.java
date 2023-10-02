package com.picpaysimplificado.exception;

public class NotificationNotSentException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public NotificationNotSentException() {
        super("Serviço de notificação está indisponível");
    }
}
