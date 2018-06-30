package com.example.tang.jwt_oauth_tin.repositories;


import com.example.tang.jwt_oauth_tin.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Account save(Account account);
    void deleteAccountById(Long id);
}
