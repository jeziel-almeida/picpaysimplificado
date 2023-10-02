package com.picpaysimplificado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.dto.TransactionDTO;
import com.picpaysimplificado.service.TransactionService;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Transaction createTransaction(@RequestBody TransactionDTO transactionDTO) throws JsonMappingException, JsonProcessingException {
        return transactionService.createTransaction(transactionDTO);
    }
}
