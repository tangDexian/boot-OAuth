package com.example.tang.jwt_oauth_tin.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity(debug = true) // turns off auto security
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    // configuring web based security for specific http request
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().disable() // disable form authentication
                .anonymous().disable() // disable anonymous user
                .httpBasic().and()
                .authorizeRequests().anyRequest().denyAll(); // denying all access
    }

    @Override
    // place to configure the default authenticationManager
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("dexian").password("{noop}geekbeta").roles("USER").and()
                .withUser("satomi").password("{noop}geekbeta").authorities("ROLE_ADMIN");


    }

    @Bean // exposing default authenticationManagerBean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
