package com.machines.vending.application.apirest.controllers.deposit;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/",
        consumes = "application/json",
        produces = "application/json")
public class DepositController {
    private final AddDepositCommand addDepositCommand;
    private final ResetDepositCommand resetDepositCommand;

    @PostMapping(value = "/deposit")
    @ResponseStatus(OK)
    public void addDeposit(Authentication authentication,
                           @RequestBody Deposit deposit) throws InvalidCoinException {
        final User user = (User) authentication.getPrincipal();
        addDepositCommand.add(deposit.getAmount()).to(Deposit.builder().buyerId(user.getId()).build());
    }

    @PutMapping(value = "/reset")
    @ResponseStatus(OK)
    public void resetDeposit(Authentication authentication) {
        final User user = (User) authentication.getPrincipal();
        resetDepositCommand.reset(Deposit.builder().buyerId(user.getId()).build());
    }
}
