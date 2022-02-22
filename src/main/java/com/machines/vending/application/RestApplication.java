package com.machines.vending.application;

import com.machines.vending.application.apirest.configuration.ApiRestApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.machines.vending.application.apirest.*")
@Import(ApiRestApplicationConfiguration.class)
public class RestApplication {
    public void initialize(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
