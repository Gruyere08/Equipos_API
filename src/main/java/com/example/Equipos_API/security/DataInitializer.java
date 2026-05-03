package com.example.Equipos_API.security;


import com.example.Equipos_API.entity.Role;
import com.example.Equipos_API.entity.User;
import com.example.Equipos_API.repository.UserRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        System.out.println("Secure key: ");
        System.out.println(Encoders.BASE64.encode(key.getEncoded()));

        if (userRepository.findByUsername("admin").isEmpty()) {

            User user = new User();
            user.setUsername("admin");

            // IMPORTANT: encode password
            user.setPassword(
                    passwordEncoder.encode("1234")
            );

            user.setRole(Role.ROLE_USER);

            userRepository.save(user);

            System.out.println("Test user created!");
        }
    }
}
