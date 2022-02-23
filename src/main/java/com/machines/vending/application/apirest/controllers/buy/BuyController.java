package com.machines.vending.application.apirest.controllers.buy;

import com.machines.vending.application.apirest.controllers.BaseController;
import com.machines.vending.domain.commands.buy.BuyCommand;
import com.machines.vending.domain.exceptions.deposit.NotEnoughDepositException;
import com.machines.vending.domain.exceptions.product.NotEnoughProductAmountAvailableException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import com.machines.vending.domain.exceptions.session.NoActiveSessionException;
import com.machines.vending.domain.models.Purchase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
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
public class BuyController extends BaseController {
    private final BuyCommand buyCommand;

    @PostMapping(value = "/buy")
    @ResponseStatus(OK)
    public void addDeposit(@RequestHeader(TOKEN_KEY) String token,
                           @RequestBody Purchase purchase) throws NotEnoughProductAmountAvailableException, NotEnoughDepositException, ProductNotFoundException, NoActiveSessionException {
        final Integer userId = getUserInformationFromToken(token);
        buyCommand.execute(userId, purchase);
    }
}
