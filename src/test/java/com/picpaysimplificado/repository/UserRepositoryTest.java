package com.picpaysimplificado.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.dto.UserDTO;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should get user successfully from database")
    void testFindUserByDocumentCase1() {

        String document = "99999999901";

        UserDTO data = new UserDTO("Jeziel", "Almeida", document, "jzl@gmail.com", "87654321", new BigDecimal(100), UserType.COMMON);
        this.createUser(data);

        Optional<User> result = userRepository.findUserByDocument(document);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get user from database when user don't exist")
    void testFindUserByDocumentCase2() {

        String document = "99999999901";

        Optional<User> result = userRepository.findUserByDocument(document);

        assertThat(result.isEmpty()).isTrue();
    }

    public User createUser(UserDTO userDTO) {
        User newUser = new User(userDTO);
        entityManager.persist(newUser);
        return newUser;
    }
}
