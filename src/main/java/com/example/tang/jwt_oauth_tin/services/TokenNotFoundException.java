package com.example.tang.jwt_oauth_tin.services;

public class TokenNotFoundException extends Exception {
    public TokenNotFoundException(String message) {
        super(message);
    }
}
