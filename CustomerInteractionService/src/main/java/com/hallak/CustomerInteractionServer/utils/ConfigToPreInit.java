package com.hallak.CustomerInteractionServer.utils;

import com.hallak.CustomerInteractionServer.entities.RoleType;
import com.hallak.CustomerInteractionServer.entities.User;
import com.hallak.CustomerInteractionServer.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class ConfigToPreInit {

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            User admin = new User();
            admin.setEmail("admin@email.com");
            admin.setCpf("00000000000");
            admin.setUsername("admin");
            admin.setPassword(new BCryptPasswordEncoder().encode("123456"));
            admin.setRoles(Set.of(RoleType.ROLE_ADMIN));
            userRepository.save(admin);
        };
    }
}









