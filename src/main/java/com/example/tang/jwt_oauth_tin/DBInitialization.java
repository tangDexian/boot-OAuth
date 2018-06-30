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

	private AccountService accountService;

	@Autowired
	public DBInitialization(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
    public void run(String... args) throws Exception {
		System.out.println("inside of run");
		Arrays.asList("user,admin,robert,ana".split(",")).forEach(
						username -> {
							Account acct = new Account();
							acct.setUsername(username);
							acct.setPassword("geekbeta");
							acct.setFirstName(username);
							acct.setLastName("lastName");
							acct.grantAuthority(Role.ROLE_USER);
							if (username.equals("admin"))
								acct.grantAuthority(Role.ROLE_ADMIN);

							System.out.println("##############   " + acct + " ############");
							accountService.registerUser(acct);
						}
		);

		System.out.println("after DB init");
    }
}
