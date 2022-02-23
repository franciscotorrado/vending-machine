package com.machines.vending.application.apirest.controllers.deposit;

import com.machines.vending.application.apirest.controllers.BaseController;
import com.machines.vending.domain.commands.deposit.AddDepositCommand;
import com.machines.vending.domain.commands.deposit.ResetDepositCommand;
import com.machines.vending.domain.models.Deposit;
import com.machines.vending.domain.models.Role;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/",
        consumes = "application/json",
        produces = "application/json")
public class DepositController extends BaseController {
    private final AddDepositCommand addDepositCommand;
    private final ResetDepositCommand resetDepositCommand;

    @PostMapping(value = "/deposit")
    @ResponseStatus(OK)
    public void addDeposit(@RequestHeader(TOKEN_KEY) String token,
                           @RequestBody Deposit deposit) throws Exception {
        final Integer userId = checkRights(token, Role.BUYER);
        addDepositCommand.add(deposit.getAmount()).to(Deposit.builder().buyerId(userId).build());
    }

    @PutMapping(value = "/reset")
    @ResponseStatus(OK)
    public void resetDeposit(@RequestHeader(TOKEN_KEY) String token) throws Exception {
        final Integer userId = checkRights(token, Role.BUYER);
        resetDepositCommand.reset(Deposit.builder().buyerId(userId).build());
    }
}
