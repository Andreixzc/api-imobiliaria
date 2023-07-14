package com.example.Imobiliaria.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class MySecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        final String ADMIN_ROLE = "ROLE_ADMIN";
        http.csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers(HttpMethod.POST, "/users")
        .permitAll()
        .requestMatchers(HttpMethod.POST, "/users/login")
        .permitAll()
        .requestMatchers(HttpMethod.GET, "/users")
        .hasAnyAuthority(ADMIN_ROLE)
        .requestMatchers(HttpMethod.PATCH, "/users/{id}")
        .permitAll()
        .requestMatchers(HttpMethod.DELETE, "/users/{id}")
        .hasAnyAuthority(ADMIN_ROLE)
        .requestMatchers(HttpMethod.POST, "/categories")
        .hasAnyAuthority(ADMIN_ROLE)
        .requestMatchers(HttpMethod.GET, "/categories")
        .permitAll()
        .requestMatchers(HttpMethod.GET, "/realEstate/{id}")
        .permitAll()
        .requestMatchers(HttpMethod.POST, "/realEstate")
        .hasAnyAuthority(ADMIN_ROLE)
        .requestMatchers(HttpMethod.GET, "/realEstate")
        .permitAll()
        .requestMatchers(HttpMethod.POST, "/schedule")
        .permitAll()
        .requestMatchers(HttpMethod.GET, "/schedule/{id}")
        .hasAnyAuthority(ADMIN_ROLE)
        .anyRequest().permitAll().and().cors();
        http.addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
        
    }
}
