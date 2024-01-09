package com.example.demo.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String userDNI;
    private String password;
}
