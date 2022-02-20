package com.machines.vending.domain.commands;

import com.machines.vending.domain.exceptions.ProductNotFoundException;
import com.machines.vending.domain.models.Product;

public interface ReadProductCommand {
    Product execute(Product productToRead) throws ProductNotFoundException;
}
