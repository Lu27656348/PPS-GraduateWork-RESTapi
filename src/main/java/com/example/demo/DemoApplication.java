package com.example.demo;

import com.example.demo.interfaces.MessageResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class DemoApplication {

	public String PORT = System.getenv("PORT");
	public String DATABASE_URL = System.getenv("DATABASE_URL");
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
