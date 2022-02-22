package com.machines.vending.application.apirest.controllers.buy;

import com.machines.vending.domain.exceptions.deposit.NotEnoughDepositException;
import com.machines.vending.domain.exceptions.product.NotEnoughProductAmountAvailableException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@AllArgsConstructor
@RestControllerAdvice("com.machines.vending.application.apirest.controllers.buy")
public class BuyControllerExceptionsHandler {
    @ExceptionHandler({
            NotEnoughProductAmountAvailableException.class,
            NotEnoughDepositException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleConflictExceptions(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler({ProductNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRequestExceptions(Exception e) {
        return e.getMessage();
    }
}
