package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.exceptions.product.NotEnoughProductAmountAvailableException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import com.machines.vending.domain.models.Product;
import com.machines.vending.infrastructure.persistence.entities.ProductEntity;
import com.machines.vending.infrastructure.persistence.mappers.ProductMapper;
import com.machines.vending.infrastructure.persistence.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ReduceProductAmountAvailableCommandImpl implements ReduceProductAmountAvailableCommand {
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public void execute(final int productId,
                        final int amountToReduce) throws NotEnoughProductAmountAvailableException, ProductNotFoundException {
        final ProductEntity productEntity = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        final Product product = ProductMapper.fromEntity(productEntity).toModel();
        product.reduceAvailableAmount(amountToReduce);
        productRepository.updateAmountAvailable(productEntity.getId(), product.getAmountAvailable());
    }
}
