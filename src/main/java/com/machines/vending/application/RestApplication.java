package com.machines.vending.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApplication {
    public void initialize(String[] args) {
            SpringApplication.run(RestApplication.class, args);
    }
}
