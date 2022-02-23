package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.exceptions.product.CreateProductWithGivenIdException;
import com.machines.vending.domain.exceptions.product.NotValidProductCostException;
import com.machines.vending.domain.exceptions.product.NotValidProductNameException;
import com.machines.vending.domain.models.IdInfo;
import com.machines.vending.domain.models.Product;
import com.machines.vending.infrastructure.persistence.entities.ProductEntity;
import com.machines.vending.infrastructure.persistence.mappers.ProductMapper;
import com.machines.vending.infrastructure.persistence.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class CreateProductCommandImpl implements CreateProductCommand {
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public IdInfo execute(final Product product) throws NotValidProductCostException, NotValidProductNameException, CreateProductWithGivenIdException {
        checkIsNew(product);
        product.validate();

        final ProductEntity productEntity = ProductMapper.fromModel(product).toEntity();

        final ProductEntity createdProduct = productRepository.save(productEntity);

        return IdInfo.builder().id(createdProduct.getId()).build();
    }

    private void checkIsNew(final Product product) throws CreateProductWithGivenIdException {
        if (!isNull(product.getId())) {
            throw new CreateProductWithGivenIdException();
        }
    }
}
