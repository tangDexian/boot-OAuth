package com.example.tang.jwt_oauth_tin.apiModel;

import lombok.Value;

@Value
public class RegisterForm {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
