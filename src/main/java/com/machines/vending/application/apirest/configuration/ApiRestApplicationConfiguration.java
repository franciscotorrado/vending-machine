package com.machines.vending.application.apirest.configuration;

import com.machines.vending.domain.commands.deposit.AddDepositCommand;
import com.machines.vending.domain.commands.deposit.AddDepositCommandImpl;
import com.machines.vending.domain.commands.user.ReadUserCommand;
import com.machines.vending.domain.commands.user.ReadUserCommandImpl;
import com.machines.vending.infraestructure.persistence.repositories.DepositRepository;
import com.machines.vending.infraestructure.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ApiRestApplicationConfiguration {
    private DepositRepository depositRepository;
    private UserRepository userRepository;

    @Bean
    public AddDepositCommand addDepositCommand() {
        return new AddDepositCommandImpl(depositRepository);
    }

    @Bean
    public ReadUserCommand readUserCommand() {
        return new ReadUserCommandImpl(userRepository);
    }
}
