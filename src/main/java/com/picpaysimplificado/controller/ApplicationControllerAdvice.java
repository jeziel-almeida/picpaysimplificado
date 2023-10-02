package com.picpaysimplificado.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.picpaysimplificado.exception.InsufficientBalanceException;
import com.picpaysimplificado.exception.NotificationNotSentException;
import com.picpaysimplificado.exception.RecordNotFoundException;
import com.picpaysimplificado.exception.TransactionNotAllowedException;
import com.picpaysimplificado.exception.UserNotAllowedException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return "Usuário já cadastrado";
    }
    
    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RecordNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInsufficientBalanceException(InsufficientBalanceException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(NotificationNotSentException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleNotificationNotSentException(NotificationNotSentException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(TransactionNotAllowedException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleTransactionNotAllowedException(TransactionNotAllowedException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(UserNotAllowedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleUserNotAllowedException(UserNotAllowedException ex) {
        return ex.getMessage();
    }
}
