package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.models.ProductItem;
import com.machines.vending.domain.models.Role;

import java.util.List;

public interface ReadAllProductsCommand {
    List<ProductItem> execute(int userId, Role role);
}
