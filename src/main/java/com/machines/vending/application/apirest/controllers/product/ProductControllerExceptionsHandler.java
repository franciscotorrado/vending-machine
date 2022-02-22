package com.machines.vending.application.apirest.controllers.product;

import com.machines.vending.domain.exceptions.product.CreateProductWithGivenIdException;
import com.machines.vending.domain.exceptions.product.NotValidProductCostException;
import com.machines.vending.domain.exceptions.product.NotValidProductNameException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@AllArgsConstructor
@RestControllerAdvice("com.machines.vending.application.apirest.controllers.product")
public class ProductControllerExceptionsHandler {
    @ExceptionHandler({
            CreateProductWithGivenIdException.class,
            NotValidProductCostException.class,
            NotValidProductNameException.class,
            ProductNotFoundException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleProductExceptions(Exception e) {
        return e.getMessage();
    }
}
