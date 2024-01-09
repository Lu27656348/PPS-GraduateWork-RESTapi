package com.example.demo.controller;

import com.example.demo.interfaces.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @CrossOrigin( origins = "https://professional-practices-system.vercel.app")
    @GetMapping({"/","/home"})
    public ResponseEntity<MessageResponse> welcomeMessage(){
        return ResponseEntity.ok(new MessageResponse("Welcome to Luis Somoza PPS REST API In Java, have a good time <3"));
    }
}
