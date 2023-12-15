package com.LogbookApp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MvcSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf->csrf.disable()).authorizeHttpRequests((request) -> request
                .requestMatchers("/resources/**", "/account/**", "/home/**")
                .permitAll()
                .anyRequest().authenticated()
        ).formLogin((form) -> form
                .loginPage("/account/loginForm")
                .loginProcessingUrl("/login")
                .failureUrl("/account/loginFailed")
        ).logout((logout) -> logout
                .logoutUrl("/logout")
        ).exceptionHandling((exception) -> exception
                .accessDeniedPage("/account/accessDenied")
        );
        return httpSecurity.build();
    }
}
