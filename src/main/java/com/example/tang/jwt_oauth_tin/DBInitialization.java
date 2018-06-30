package com.example.tang.jwt_oauth_tin;

import com.example.tang.jwt_oauth_tin.models.Account;
import com.example.tang.jwt_oauth_tin.models.Role;
import com.example.tang.jwt_oauth_tin.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DBInitialization implements CommandLineRunner {

	@Autowired
	private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
		Arrays.asList(
				"user,admin,robert,ana".split(".")).forEach(
						username -> {
							System.out.println("##############   " + username + " ############");
							Account acct = new Account();
							acct.setUsername(username);
							acct.setPassword("geekbeta");
							acct.setFirstName(username);
							acct.setLastName("lastName");
							acct.grantAuthority(Role.ROLE_USER);
							if (username.equals("admin"))
								acct.grantAuthority(Role.ROLE_ADMIN);
							accountService.registerUser(acct);
						}
		);
    }
}
