package com.picpaysimplificado.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.exception.InsufficientBalance;
import com.picpaysimplificado.exception.RecordNotFoundException;
import com.picpaysimplificado.exception.TransactionNotAllowed;
import com.picpaysimplificado.repository.UserRepository;

@Service
public class UserService {
// Classe onde é implementada as regras de negócio
    
    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws TransactionNotAllowed, InsufficientBalance {
        
        if(sender.getUserType() == UserType.MERCHANT) {
            throw new TransactionNotAllowed(UserType.MERCHANT);
        }

        if(sender.getBalance().compareTo(amount) < 0) { //if balance is less than amount
            throw new InsufficientBalance();
        }
    }

    public User findUserById(Long id) throws RecordNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
