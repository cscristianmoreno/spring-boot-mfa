package com.spring.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.app.services.GoogleCredentialsRepository;
import com.warrenstrange.googleauth.GoogleAuthenticator;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomOncePerRequestFilter customOncePerRequestFilter;

    @Autowired
    private GoogleCredentialsRepository googleCredentialsRepository;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return ( 
            httpSecurity
                .authorizeHttpRequests((req) -> {
                    req.requestMatchers("/auth/*", "/users/save", "/actuator/**").permitAll();
                    req.anyRequest().authenticated();
                })
                .csrf((csrf) -> {
                    csrf.disable();
                })
                .formLogin((login) -> {
                    login.disable();
                })
                .addFilterBefore(customOncePerRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((session) -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.NEVER);
                })
                .httpBasic(Customizer.withDefaults())
            .build()
        );
    }

    @Bean
    GoogleAuthenticator googleAuthenticatorConfigurer() {
        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
        googleAuthenticator.setCredentialRepository(googleCredentialsRepository);
        return googleAuthenticator;
    }
}
