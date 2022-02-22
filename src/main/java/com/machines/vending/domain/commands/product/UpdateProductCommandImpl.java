package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.exceptions.product.NotValidProductCostException;
import com.machines.vending.domain.exceptions.product.NotValidProductNameException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import com.machines.vending.domain.models.Product;
import com.machines.vending.infrastructure.persistence.entities.ProductEntity;
import com.machines.vending.infrastructure.persistence.mappers.ProductMapper;
import com.machines.vending.infrastructure.persistence.repositories.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateProductCommandImpl implements UpdateProductCommand {
    private final ProductRepository productRepository;

    @Override
    public void execute(final Product product) throws NotValidProductCostException, NotValidProductNameException, ProductNotFoundException {
        product.validate();

        if (!productRepository.existsById(product.getId())) {
            throw new ProductNotFoundException();
        }

        final ProductEntity productEntity = ProductMapper.fromModel(product).toEntity();

        productRepository.save(productEntity);
    }
}
