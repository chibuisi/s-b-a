package com.kelvin.spring.batch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        List users = new ArrayList<>();
        users.add(User.withDefaultPasswordEncoder()
                .username("kelvin")
                .password("1234;")
                .roles("ADMIN")
                .build());
        return new InMemoryUserDetailsManager(users);
    }
}
