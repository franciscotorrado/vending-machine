package com.machines.vending.infrastructure.persistence.mappers;

import com.machines.vending.domain.models.ProductItem;
import com.machines.vending.infrastructure.persistence.entities.ProductEntity;

public final class ProductItemMapper {
    public static EntityMapper<ProductItem> fromEntity(final ProductEntity productEntity) {
        return () -> ProductItem.builder()
                .id(productEntity.getId())
                .productName(productEntity.getProductName())
                .cost(productEntity.getCost())
                .amountAvailable(productEntity.getAmountAvailable())
                .build();
    }
}
