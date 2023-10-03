package com.picpaysimplificado.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import com.picpaysimplificado.domain.user.UserType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
    Long id,
    @NotNull @NotBlank String firstName,
    @NotNull @NotBlank String lastName,
    @NotBlank @Length(min = 11, max = 20) String document,
    @Email @NotBlank String email,
    @NotNull @NotBlank @Length(min = 8) String password,
    @NotNull BigDecimal balance,
    @NotNull UserType userType
) {
    
}
