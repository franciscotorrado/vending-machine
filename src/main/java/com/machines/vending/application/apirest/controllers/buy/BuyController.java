package com.machines.vending.application.apirest.controllers.buy;

import com.machines.vending.domain.commands.buy.BuyCommand;
import com.machines.vending.domain.exceptions.deposit.NotEnoughDepositException;
import com.machines.vending.domain.exceptions.product.NotEnoughProductAmountAvailableException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import com.machines.vending.domain.models.Purchase;
import com.machines.vending.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
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
public class BuyController {
    private final BuyCommand buyCommand;

    @PostMapping(value = "/buy")
    @ResponseStatus(OK)
    public void addDeposit(Authentication authentication,
                           @RequestBody Purchase purchase) throws NotEnoughProductAmountAvailableException, NotEnoughDepositException, ProductNotFoundException {
        final User user = (User) authentication.getPrincipal();
        buyCommand.execute(user.getId(), purchase);
    }
}
