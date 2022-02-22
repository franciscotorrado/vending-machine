package com.machines.vending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.machines.vending.application.*",
        "com.machines.vending.domain.*",
        "com.machines.vending.infrastructure.*"
})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
