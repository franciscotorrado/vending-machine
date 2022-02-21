package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.models.Product;

public interface DeleteProductCommand {
    void execute(Product product);
}
