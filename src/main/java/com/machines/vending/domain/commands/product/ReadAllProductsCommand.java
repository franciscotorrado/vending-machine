package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.models.Product;

import java.util.List;

public interface ReadAllProductsCommand {
    List<Product> execute();
}
