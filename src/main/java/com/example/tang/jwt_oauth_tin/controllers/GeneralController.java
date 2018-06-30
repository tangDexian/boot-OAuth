package com.example.tang.jwt_oauth_tin.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class GeneralController {

    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/api/test")
    public RestMsg apitest() {
        return new RestMsg("Hello dexian");
    }

    @GetMapping(value = "/api/hello", produces = "application/json")
    public RestMsg helloUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new RestMsg(String.format("Hello %s!", username));
    }

    @GetMapping("/api/admin")
    public RestMsg helloAdmin(Principal principal) {
        return new RestMsg(String.format("Welcome '%s'", principal.getName()));
    }



    public static class RestMsg {
        private String msg;

        public RestMsg(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
