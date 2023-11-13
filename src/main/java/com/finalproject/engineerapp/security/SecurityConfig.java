package com.finalproject.engineerapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user1 = User.builder()
                .username("neringa")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.builder()
                .username("luke")
                .password("{noop}user")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/engineers").hasAnyRole("ADMIN", "USER")
                    .antMatchers(HttpMethod.GET, "/engineers/**").hasAnyRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/engineers").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/engineers").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/engineers/**").hasRole("ADMIN");
        http.httpBasic();
        http.csrf().disable();
    }
}
