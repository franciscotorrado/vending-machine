package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.models.ProductItem;

import java.util.List;

public interface ReadAllProductsCommand {
    List<ProductItem> execute();
}
