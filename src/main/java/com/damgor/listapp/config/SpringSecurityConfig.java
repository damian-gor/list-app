package com.damgor.listapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Damian").password("pass").roles("ADMIN","USER");
        auth.inMemoryAuthentication().withUser("Daria").password("pass").roles("USER");
        auth.inMemoryAuthentication().withUser("Ania").password("pass").roles("USER");
        auth.inMemoryAuthentication().withUser("Riczi").password("pass").roles("USER");
        auth.inMemoryAuthentication().withUser("Konrad").password("pass").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.authorizeRequests().anyRequest() // do kazdego request bedzie wymagana autentykacja
                .fullyAuthenticated()
                .and().httpBasic();
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder(){
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
