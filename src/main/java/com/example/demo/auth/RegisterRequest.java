package com.example.demo.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String userDNI;
    private String password;
    private String userfirstname;
    private String userlastname;
    private String useremailucab;
}
