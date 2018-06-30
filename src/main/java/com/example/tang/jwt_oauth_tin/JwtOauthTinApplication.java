package com.example.tang.jwt_oauth_tin;

import com.example.tang.jwt_oauth_tin.models.Account;
import com.example.tang.jwt_oauth_tin.models.Role;
import com.example.tang.jwt_oauth_tin.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
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

//	@Bean
//	CommandLineRunner init(AccountService accountService) {
//
//		return (evt) -> Arrays.asList(
//				"user,admin,robert,ana".split(".")).forEach(
//						username -> {
//							System.out.println("##############   " + username + " ############");
//							Account acct = new Account();
//							acct.setUsername(username);
//							acct.setPassword("geekbeta");
//							acct.setFirstName(username);
//							acct.setLastName("lastName");
//							acct.grantAuthority(Role.ROLE_USER);
//							if (username.equals("admin"))
//								acct.grantAuthority(Role.ROLE_ADMIN);
//							accountService.registerUser(acct);
//						}
//		);
//	}
}
