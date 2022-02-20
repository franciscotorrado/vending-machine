package com.machines.vending.domain.commands;

import com.machines.vending.domain.exceptions.CreateProductWithGivenIdException;
import com.machines.vending.domain.exceptions.NotValidProductCostException;
import com.machines.vending.domain.exceptions.NotValidProductNameException;
import com.machines.vending.domain.models.Product;

public interface CreateProductCommand {
    Product execute(final Product product) throws NotValidProductCostException, NotValidProductNameException, CreateProductWithGivenIdException;
}
