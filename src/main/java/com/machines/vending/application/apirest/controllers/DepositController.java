package com.machines.vending.application.apirest.controllers;

import com.machines.vending.domain.commands.deposit.AddDepositCommand;
import com.machines.vending.domain.commands.deposit.ResetDepositCommand;
import com.machines.vending.domain.exceptions.coin.InvalidCoinException;
import com.machines.vending.domain.models.Deposit;
import com.machines.vending.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/deposit")
@AllArgsConstructor
public class DepositController {
    private final AddDepositCommand addDepositCommand;
    private final ResetDepositCommand resetDepositCommand;

    @PostMapping("/add")
    public void addDeposit(Authentication authentication,
                           @RequestBody Deposit deposit) throws InvalidCoinException {
        final User user = (User) authentication.getPrincipal();
        addDepositCommand.add(deposit.getAmount()).to(Deposit.builder().buyerId(user.getId()).build());
    }

    @PutMapping("/reset")
    public void resetDeposit(Authentication authentication) {
        final User user = (User) authentication.getPrincipal();
        resetDepositCommand.reset(Deposit.builder().buyerId(user.getId()).build());
    }
}
