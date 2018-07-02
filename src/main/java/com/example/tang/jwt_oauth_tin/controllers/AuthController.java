package com.example.tang.jwt_oauth_tin.controllers;

import com.example.tang.jwt_oauth_tin.apiModel.RegisterForm;
import com.example.tang.jwt_oauth_tin.models.Account;
import com.example.tang.jwt_oauth_tin.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/whatever")
public class AuthController {

    private AccountService accountService;

    @Autowired
    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public void test(Principal principal) {
        System.out.println(principal.toString());
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterForm registerForm) {
            Account acc = new Account();
            acc.setUsername(registerForm.getUsername());
            acc.setEmail(registerForm.getEmail());
            acc.setFirstName(registerForm.getFirstName());
            acc.setLastName(registerForm.getLastName());
            acc.setPassword(registerForm.getPassword());

            Account saved_acc = accountService.registerUser(acc);

            if (saved_acc != null) return ResponseEntity.ok("account created");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
