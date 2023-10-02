package com.picpaysimplificado.dto.mapper;

import org.springframework.stereotype.Component;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dto.UserDTO;

@Component
public class UserMapper {

    public User toEntity(UserDTO userDTO) {
        if(userDTO == null) return null;

        User user = new User();
        if (userDTO.id() != null) {
            user.setId(userDTO.id());
        }
        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setDocument(userDTO.document());
        user.setBalance(userDTO.balance());
        user.setEmail(userDTO.email());
        user.setUserType(userDTO.userType());
        user.setPassword(userDTO.password());

        return user;
    }
}
