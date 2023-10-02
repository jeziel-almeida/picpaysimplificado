package com.picpaysimplificado.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dto.TransactionDTO;
import com.picpaysimplificado.exception.TransactionNotAllowedException;
import com.picpaysimplificado.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transactionDTO) throws JsonMappingException, JsonProcessingException {

        User sender = userService.findUserById(transactionDTO.senderId());
        User receiver = userService.findUserById(transactionDTO.receiverId());

        userService.validateTransaction(sender, transactionDTO.value());

        boolean isAuthorized = authorizeTransaction(sender, transactionDTO.value());
        if (!isAuthorized) throw new TransactionNotAllowedException();

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.value());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        //sendNotification(sender, receiver, transactionDTO);
        
        return saveTransaction(sender, receiver, transaction);
    }

    private boolean authorizeTransaction(User sender, BigDecimal amount) throws JsonMappingException, JsonProcessingException {

        ResponseEntity<String> authorizationResponse = restTemplate.getForEntity(
                "https://gist.githubusercontent.com/jeziel-almeida/9e22096c35e42e8943df61c8ec089794/raw/540051d4e65ac802bba3bbe51ab9c97fbb0eb668/mockAuth.json",
                String.class);

        String corpo = authorizationResponse.getBody();

        // Converte o corpo da resposta em um objeto JSON
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> json = mapper.readValue(corpo, new TypeReference<Map<String, String>>() {});

        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String message = json.get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }

        return false;
    }

    private void sendNotification(User sender, User receiver, TransactionDTO transactionDTO) {

        String messageToReceiver = "Transferência de R$ " + transactionDTO.value() + " recebida com sucesso de "
                + sender.getFirstName();
        String messageToSender = "Transferência de R$ " + transactionDTO.value() + " enviada com sucesso para "
                + receiver.getFirstName();

        notificationService.sendNotification(sender, messageToReceiver);
        notificationService.sendNotification(receiver, messageToSender);
    }

    public Transaction saveTransaction(User sender, User receiver, Transaction transaction) {
        userService.saveUser(sender);
        userService.saveUser(receiver);
        return transactionRepository.save(transaction);
    }
}                                                     
