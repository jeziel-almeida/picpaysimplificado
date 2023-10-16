package com.picpaysimplificado.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpaysimplificado.domain.user.User;

public class AuthorizationService {
    
    @Autowired
    private RestTemplate restTemplate;

    public boolean authorizeTransaction(User sender, BigDecimal amount) throws JsonMappingException, JsonProcessingException {

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
}
