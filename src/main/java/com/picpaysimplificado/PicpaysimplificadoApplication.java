package com.picpaysimplificado;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.repository.UserRepository;

@SpringBootApplication
public class PicpaysimplificadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicpaysimplificadoApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository) {
		return args -> {
			userRepository.deleteAll();

			User user1 = new User();
			user1.setFirstName("Jeziel");
			user1.setLastName("Almeida");
			user1.setDocument("712.858.909-12");
			user1.setEmail("jeziel.almeida@gmail.com");
			user1.setBalance(new BigDecimal(1200));
			user1.setPassword("12345678");
			user1.setUserType(UserType.COMMON);
			
			User user2 = new User();
			user2.setFirstName("Adiel");
			user2.setLastName("Almeida");
			user2.setDocument("723.855.939-12");
			user2.setEmail("adiel.almeida@gmail.com");
			user2.setBalance(new BigDecimal(1300));
			user2.setPassword("12345678");
			user2.setUserType(UserType.MERCHANT);

			List<User> users = new ArrayList<>();
			users.add(user1);
			users.add(user2);

			userRepository.saveAll(users);

		};
	}

}
