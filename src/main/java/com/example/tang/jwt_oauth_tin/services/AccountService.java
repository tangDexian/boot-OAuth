package com.example.tang.jwt_oauth_tin.services;

import com.example.tang.jwt_oauth_tin.models.Account;
import com.example.tang.jwt_oauth_tin.models.Role;
import com.example.tang.jwt_oauth_tin.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Primary
public class AccountService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername(username);
        if (account.isPresent())
            return account.get();
        else
            throw new UsernameNotFoundException(String.format("Username [%s] not found", username));
    }

    // don't quite understand the meaning of this one
    public Account findAccountByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername(username);
        if (account.isPresent())
            return account.get();
        else
            throw new UsernameNotFoundException(String.format("Username [%s] not found", username));
    }

    public Account registerUser(Account account) {
        System.out.println("in register " + account);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.grantAuthority(Role.ROLE_USER);
        return accountRepository.save(account);
    }

    @Transactional // two DB operation, transactional is needed
    public void removeAuthenticatedAccount() throws UsernameNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = findAccountByUsername(username);
        accountRepository.deleteAccountById(account.getId());
    }
}
