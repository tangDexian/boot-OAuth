package com.example.tang.jwt_oauth_tin.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true) // turns off auto security
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsService userDetailsService;

    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);

        return authenticationProvider;
    }

    @Override
    // configuring web based security for specific http request
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/h2-console").permitAll().and()
                .formLogin().disable() // disable form authentication
                .anonymous().disable() // disable anonymous user
                .httpBasic().and()
                .authorizeRequests().anyRequest().authenticated(); // denying all access

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    // place to configure the default authenticationManager
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

//                .inMemoryAuthentication()
//                .withUser("dexian").password("{noop}geekbeta").roles("USER").and()
//                .withUser("satomi").password("{noop}geekbeta").authorities("ROLE_ADMIN");


    }

    @Bean // exposing default authenticationManagerBean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
