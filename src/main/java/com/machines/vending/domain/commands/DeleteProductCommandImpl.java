package com.machines.vending.domain.commands;

import com.machines.vending.domain.models.Product;
import com.machines.vending.infraestructure.persistence.entities.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteProductCommandImpl implements DeleteProductCommand {
    private final ProductRepository productRepository;

    @Override
    public void execute(final Product product) {
        productRepository.deleteById(product.getId());
    }
}
