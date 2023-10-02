package com.picpaysimplificado.dto;

import java.math.BigDecimal;

import com.picpaysimplificado.domain.user.UserType;

public record UserDTO(
    Long id,
    String firstName,
    String lastName,
    String document,
    BigDecimal balance,
    String email,
    String password,
    UserType userType
) {
    
}
