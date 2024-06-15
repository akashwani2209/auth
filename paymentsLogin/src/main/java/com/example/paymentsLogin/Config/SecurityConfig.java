package com.example.paymentsLogin.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Marks this class as a source of bean definitions for the application context
@EnableWebSecurity // Enables Spring Security's web security support and provides Spring MVC integration
public class SecurityConfig {

    // Define the SecurityFilterChain bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configure authorization rules
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        // Allow unauthenticated access to these endpoints
                        .requestMatchers("/register", "/login", "/oauth2/**").permitAll()
                        // Require authentication for all other requests
                        .anyRequest().authenticated()
                )
                // Configure form-based authentication
                .formLogin(formLogin -> formLogin
                        // Specify the custom login page URL
                        .loginPage("/login")
                        // Redirect to /home upon successful login
                        .defaultSuccessUrl("/home", true)
                )
                // Configure OAuth2 login
                .oauth2Login(oauth2Login -> oauth2Login
                        // Specify the custom login page URL for OAuth2 authentication
                        .loginPage("/login")
                        // Redirect to /home upon successful OAuth2 login
                        .defaultSuccessUrl("/home", true)
                );

        return http.build();
    }

    // Define a PasswordEncoder bean for securely hashing passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use BCryptPasswordEncoder for hashing passwords
        return new BCryptPasswordEncoder();
    }
}
