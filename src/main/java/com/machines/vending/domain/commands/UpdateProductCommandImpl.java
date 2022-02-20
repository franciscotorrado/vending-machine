package com.machines.vending.domain.commands;

import com.machines.vending.domain.exceptions.NotValidProductCostException;
import com.machines.vending.domain.exceptions.NotValidProductNameException;
import com.machines.vending.domain.exceptions.ProductNotFoundException;
import com.machines.vending.domain.models.Product;
import com.machines.vending.infraestructure.persistence.entities.ProductEntity;
import com.machines.vending.infraestructure.persistence.entities.ProductMapper;
import com.machines.vending.infraestructure.persistence.entities.ProductRepository;
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
