package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication {

	public String PORT = System.getenv("PORT");
	public String DATABASE_URL = System.getenv("DATABASE_URL");
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
