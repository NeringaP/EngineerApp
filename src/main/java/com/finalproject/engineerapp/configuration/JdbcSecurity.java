package com.finalproject.engineerapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

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
        http.cors().and().csrf().disable();
        http.formLogin();

        //http.authorizeRequests()
        //  .antMatchers("/welcome").hasRole("BOARD");

        //konfiguracija matomumams eina cia
        http
                .authorizeRequests()
                //.antMatchers("/projects").authenticated()
                //.antMatchers("/projects").hasRole("BOARD")


                .antMatchers("/home").permitAll()
                .antMatchers("/houses", "/projects", "/engineers", "/creators").hasAnyAuthority("CREATOR", "USER")
                .antMatchers("/houses/**", "/projects/**", "/engineers/**", "/creators/**").hasAuthority("CREATOR")
                .and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                .cors().disable();
    }

}
