package com.example.demo;

import com.example.demo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;

    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Working");
        this.userService.register("first", 20);
    }
}
