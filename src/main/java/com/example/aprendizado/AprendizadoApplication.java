package com.example.aprendizado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AprendizadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AprendizadoApplication.class, args);
	}

	@RequestMapping("/")
	public String home(){
		return "Pessa em condições";
	}

}