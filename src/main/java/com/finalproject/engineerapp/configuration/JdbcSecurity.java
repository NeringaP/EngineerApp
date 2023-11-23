package com.finalproject.engineerapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class JdbcSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "SELECT username, password, enabled from user where username = ?")
                .authoritiesByUsernameQuery(
                        "SELECT u.username, a.authority " +
                                "FROM authorities a, user u " +
                                "WHERE u.username = ? " +
                                "AND u.id = a.user_id"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //konfiguracija matomumams eina cia
        http
                .authorizeRequests()
                //.antMatchers("/projects").authenticated()
                //.antMatchers("/projects").hasRole("BOARD")


                .antMatchers("/home", "/signin", "/register", "/api/**").permitAll()
                .antMatchers("/houses/list", "/projects/list", "/engineers/list", "/engineers/export/pdf", "/creators/list", "/welcome").hasAnyAuthority(
                        "CREATOR", "USER")
                .antMatchers("/houses/**", "/projects/**", "/engineers/**", "/creators/**", "/users/**").hasAuthority("CREATOR")
                .antMatchers("/h2-console/**").permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**", "/api/**")
                .and()
                .cors().disable();

        http.formLogin(form ->
                form
                        .loginPage("/showLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                        .defaultSuccessUrl("/welcome",true)
        );
        http
                .logout(logout -> logout
                        .permitAll()
                        .logoutSuccessUrl("/home"));

        http.exceptionHandling()
                .accessDeniedPage("/access-denied");

    }

}
