package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.models.Product;
import com.machines.vending.infrastructure.persistence.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteProductCommandImpl implements DeleteProductCommand {
    private final ProductRepository productRepository;

    @Override
    public void execute(final Product product) {
        productRepository.deleteById(product.getId());
    }
}
