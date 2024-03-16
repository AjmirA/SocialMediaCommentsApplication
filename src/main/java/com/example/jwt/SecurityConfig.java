/*
package com.example.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtAuthorizationFilter jwtAuthorizationFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests(auth-> auth.requestMatchers("/api/healthcheck/**").permitAll()
                .anyRequest().authenticated());

        http.addFilterAfter(jwtAuthorizationFilter,JwtAuthorizationFilter.class);

        return http.build();
    }
}

*/
