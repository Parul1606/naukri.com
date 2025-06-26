package com.naukri.central_api.security;

import com.naukri.central_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class AuthConfiguration {
    AuthFilter authFilter;

    @Autowired
    public AuthConfiguration(AuthFilter authFilter){
        this.authFilter = authFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // CSRF disabled because we're using token-based authentication (JWT)
                .csrf().disable()

                // Define which endpoints are public and which require authentication
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/central/company/register", "/api/v1/central/user/register", "api/v1/central/company/accept-invitation/**"
                        ).permitAll() // Public endpoints
                        .anyRequest().authenticated() // All other endpoints require authentication
                )

                // Configure session management to be stateless, since we're using JWTs
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Add our custom JWT authentication filter before Spring’s built-in authentication filter
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)

                // Finalize and build the security configuration
                .build();
    }

}

/**
 * Request → AuthFilter (token validate) → SecurityContext set → Controller access
 *                             ↓
 *                     If token invalid → No auth → 401 Unauthorized
 * */