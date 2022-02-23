package com.machines.vending.application.apirest.controllers.deposit;

import com.machines.vending.application.apirest.controllers.BaseController;
import com.machines.vending.domain.commands.deposit.AddDepositCommand;
import com.machines.vending.domain.commands.deposit.ReadDepositCommand;
import com.machines.vending.domain.commands.deposit.ResetDepositCommand;
import com.machines.vending.domain.models.Deposit;
import com.machines.vending.domain.models.DepositInfo;
import com.machines.vending.domain.models.Role;
import com.machines.vending.domain.models.security.UserSessionDetails;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping(value = "/")
public class DepositController extends BaseController {
    private final AddDepositCommand addDepositCommand;
    private final ResetDepositCommand resetDepositCommand;
    private final ReadDepositCommand readDepositCommand;

    @PostMapping(value = "/deposit", consumes = "application/json", produces = "application/json")
    @ResponseStatus(OK)
    public void addDeposit(@RequestHeader(TOKEN_KEY) String token,
                           @RequestBody Deposit deposit) throws Exception {
        final UserSessionDetails user = checkRights(token, Role.BUYER);
        addDepositCommand.add(deposit.getAmount()).to(Deposit.builder().buyerId(user.getId()).build());
    }

    @GetMapping(value = "/deposit", produces = "application/json")
    @ResponseStatus(OK)
    public DepositInfo readDeposit(@RequestHeader(TOKEN_KEY) String token) throws Exception {
        final UserSessionDetails user = checkRights(token, Role.BUYER);
        return readDepositCommand.read(Deposit.builder().buyerId(user.getId()).build());
    }

    @PutMapping(value = "/reset", consumes = "application/json", produces = "application/json")
    @ResponseStatus(OK)
    public void resetDeposit(@RequestHeader(TOKEN_KEY) String token) throws Exception {
        final UserSessionDetails user = checkRights(token, Role.BUYER);
        resetDepositCommand.reset(Deposit.builder().buyerId(user.getId()).build());
    }
}
