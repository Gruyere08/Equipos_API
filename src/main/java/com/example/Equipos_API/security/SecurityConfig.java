package com.example.Equipos_API.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF completely
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // allow EVERYTHING
                )
                .headers(headers -> headers.frameOptions(frame -> frame.disable())); // allow H2 console

        return http.build();
    }
}
