package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.exceptions.product.NotValidProductCostException;
import com.machines.vending.domain.exceptions.product.NotValidProductNameException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import com.machines.vending.domain.models.Product;

public interface UpdateProductCommand {
    void execute(Product product) throws NotValidProductCostException, NotValidProductNameException, ProductNotFoundException;
}
