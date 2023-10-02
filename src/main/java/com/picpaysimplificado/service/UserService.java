package com.picpaysimplificado.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.dto.UserDTO;
import com.picpaysimplificado.dto.mapper.UserMapper;
import com.picpaysimplificado.exception.InsufficientBalanceException;
import com.picpaysimplificado.exception.RecordNotFoundException;
import com.picpaysimplificado.exception.UserNotAllowedException;
import com.picpaysimplificado.repository.UserRepository;

@Service
public class UserService {
// Classe onde é implementada as regras de negócio

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserMapper userMapper;

    public void validateTransaction(User sender, BigDecimal amount) {
        
        if(sender.getUserType() == UserType.MERCHANT) {
            throw new UserNotAllowedException(UserType.MERCHANT);
        }

        if(sender.getBalance().compareTo(amount) < 0) { //if balance is less than amount
            throw new InsufficientBalanceException();
        }
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public User createUser(UserDTO user) {
        return userRepository.save(userMapper.toEntity(user));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
