package com.machines.vending.infraestructure.persistence.mappers;

import com.machines.vending.domain.models.Product;
import com.machines.vending.infraestructure.persistence.entities.ProductEntity;

public final class ProductMapper {
    public static EntityMapper<Product> fromEntity(final ProductEntity productEntity) {
        return () -> Product.builder()
                .id(productEntity.getId())
                .productName(productEntity.getProductName())
                .sellerId(productEntity.getSellerId())
                .cost(productEntity.getCost())
                .amountAvailable(productEntity.getAmountAvailable())
                .build();
    }

    public static ModelMapper<ProductEntity> fromModel(final Product product) {
        return () -> ProductEntity.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .sellerId(product.getSellerId())
                .cost(product.getCost())
                .amountAvailable(product.getAmountAvailable())
                .build();
    }
}
