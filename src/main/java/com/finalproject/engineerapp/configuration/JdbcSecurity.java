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
                        "SELECT username, password, enabled FROM users WHERE username = ?")
                .authoritiesByUsernameQuery(
//                        "SELECT u.username, a.authority " +
//                                "FROM authorities a, users u " +
//                                "WHERE u.username = ? " +
//                                "AND u.username = a.username"
                        "SELECT username, authority FROM authorities WHERE username = ?"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.formLogin();
        http.logout()
        .logoutSuccessUrl("/home");


        //http.authorizeRequests()
        //  .antMatchers("/welcome").hasRole("BOARD");

        //konfiguracija matomumams eina cia
        http
                .authorizeRequests()
                //.antMatchers("/projects").authenticated()
                //.antMatchers("/projects").hasRole("BOARD")


                .antMatchers("/home", "/signin", "/register", "/api/**").permitAll()
                .antMatchers("/houses", "/projects", "/engineers", "/engineers/export/pdf", "/creators").hasAnyAuthority(
                        "CREATOR", "USER")
                .antMatchers("/houses/**", "/projects/**", "/engineers/**", "/creators/**", "/users/**").hasAuthority("CREATOR")
                .and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**", "/api/**")
                .and()
                .cors().disable();
    }

}
