package com.machines.vending.domain.commands;

import com.machines.vending.domain.exceptions.NotValidProductCostException;
import com.machines.vending.domain.exceptions.NotValidProductNameException;
import com.machines.vending.domain.exceptions.ProductNotFoundException;
import com.machines.vending.domain.models.Product;

public interface UpdateProductCommand {
    void execute(Product product) throws NotValidProductCostException, NotValidProductNameException, ProductNotFoundException;
}
