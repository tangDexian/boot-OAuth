package com.example.tang.jwt_oauth_tin;

import com.example.tang.jwt_oauth_tin.models.Account;
import com.example.tang.jwt_oauth_tin.models.Role;
import com.example.tang.jwt_oauth_tin.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@EnableAsync
public class JwtOauthTinApplication {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Autowired
//	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(JwtOauthTinApplication.class, args);
	}

}
