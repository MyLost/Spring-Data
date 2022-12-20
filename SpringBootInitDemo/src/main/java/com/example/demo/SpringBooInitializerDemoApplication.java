package com.example.demo;

import com.example.demo.models.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class SpringBooInitializerDemoApplication {

	public static void main(String[] args) {

		System.out.println("Welcome to First project with spring framework!");

		SpringApplication.run(SpringBooInitializerDemoApplication.class, args);

		System.out.println("Bye!!!");
	}

}
