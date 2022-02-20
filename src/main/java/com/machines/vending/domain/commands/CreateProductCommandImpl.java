package com.machines.vending.domain.commands;

import com.machines.vending.domain.exceptions.CreateProductWithGivenIdException;
import com.machines.vending.domain.exceptions.NotValidProductCostException;
import com.machines.vending.domain.exceptions.NotValidProductNameException;
import com.machines.vending.domain.models.Product;
import com.machines.vending.infraestructure.persistence.entities.ProductEntity;
import com.machines.vending.infraestructure.persistence.entities.ProductMapper;
import com.machines.vending.infraestructure.persistence.entities.ProductRepository;
import lombok.AllArgsConstructor;

import static java.util.Objects.isNull;

@AllArgsConstructor
public class CreateProductCommandImpl implements CreateProductCommand {
    private final ProductRepository productRepository;

    @Override
    public Product execute(final Product product) throws NotValidProductCostException, NotValidProductNameException, CreateProductWithGivenIdException {
        checkIsNew(product);
        product.validate();

        final ProductEntity productEntity = ProductMapper.fromModel(product).toEntity();

        final ProductEntity createdProduct = productRepository.save(productEntity);

        return Product.builder().id(createdProduct.getId()).build();
    }

    private void checkIsNew(final Product product) throws CreateProductWithGivenIdException {
        if (!isNull(product.getId())) {
            throw new CreateProductWithGivenIdException();
        }
    }
}
