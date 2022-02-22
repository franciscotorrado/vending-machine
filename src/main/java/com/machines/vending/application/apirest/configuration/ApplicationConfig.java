package com.machines.vending.application.apirest.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Vending API", version = "2.0", description = "Vending Machine"))
public class ApplicationConfig {
}
