package com.picpaysimplificado.service;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.dto.TransactionDTO;
import com.picpaysimplificado.exception.TransactionNotAllowedException;
import com.picpaysimplificado.repository.TransactionRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AuthorizationService authorizationService;

    @Mock
    private NotificationService notificationService;

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        //MockitoAnnotations.initMocks(this); #Deprecated
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should creat transaction when everything is ok")
    void testCreateTransactionCase1() throws JsonMappingException, JsonProcessingException {

        User sender = new User(1L, "Maria", "Sousa", "99999999901", "maria@gmail.com", "54893687", new BigDecimal(100), UserType.COMMON);
        User receiver = new User(2L, "José", "Almeida", "22222222201", "jose@gmail.com", "79860234", new BigDecimal(200), UserType.COMMON);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authorizationService.authorizeTransaction(any(), any())).thenReturn(true);

        TransactionDTO transactionDTO = new TransactionDTO(new BigDecimal(20), 1L, 2L);
        transactionService.createTransaction(transactionDTO);

        verify(transactionRepository, times(1)).save(any());

        sender.setBalance(new BigDecimal(80));
        verify(userService, times(1)).saveUser(sender);

        receiver.setBalance(new BigDecimal(220));
        verify(userService, times(1)).saveUser(receiver);

        verify(notificationService, times(1)).sendNotification(sender, "Transferência de R$ " + transactionDTO.value() + " enviada com sucesso para "
            + receiver.getFirstName());
        verify(notificationService, times(1)).sendNotification(receiver, "Transferência de R$ " + transactionDTO.value() + " recebida com sucesso de "
            + sender.getFirstName());
        
    }

    @Test
    @DisplayName("Should throw exception when transaction is not allowed")
    void testCreateTransactionCase2() throws JsonMappingException, JsonProcessingException {

        User sender = new User(1L, "Maria", "Sousa", "99999999901", "maria@gmail.com", "54893687", new BigDecimal(100), UserType.COMMON);
        User receiver = new User(2L, "José", "Almeida", "22222222201", "jose@gmail.com", "79860234", new BigDecimal(200), UserType.COMMON);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authorizationService.authorizeTransaction(any(), any())).thenReturn(false);

        RuntimeException thrown = Assertions.assertThrows(TransactionNotAllowedException.class, () -> {
            TransactionDTO transactionDTO = new TransactionDTO(new BigDecimal(20), 1L, 2L);
            transactionService.createTransaction(transactionDTO);
        });

        Assertions.assertEquals("Transação não autorizada", thrown.getMessage());
    }
}
