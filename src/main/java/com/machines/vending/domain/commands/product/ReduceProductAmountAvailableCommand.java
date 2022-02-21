package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.exceptions.product.NotEnoughProductAmountAvailableException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;

public interface ReduceProductAmountAvailableCommand {
    void execute(int productId,
                 int amountToReduce) throws NotEnoughProductAmountAvailableException, ProductNotFoundException;
}
