package com.machines.vending.domain.commands;

import com.machines.vending.domain.models.Product;

public interface DeleteProductCommand {
    void execute(Product product);
}
