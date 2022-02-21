package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import com.machines.vending.domain.models.Product;

public interface ReadProductCommand {
    Product execute(Product productToRead) throws ProductNotFoundException;
}
