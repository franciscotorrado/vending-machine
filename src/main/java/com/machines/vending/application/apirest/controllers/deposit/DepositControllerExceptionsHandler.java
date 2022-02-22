package com.machines.vending.application.apirest.controllers.deposit;

import com.machines.vending.domain.exceptions.coin.InvalidCoinException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@AllArgsConstructor
@RestControllerAdvice("com.machines.vending.application.apirest.controllers.deposit")
public class DepositControllerExceptionsHandler {
    @ExceptionHandler(InvalidCoinException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidCoinException(Exception e) {
        return e.getMessage();
    }
}
