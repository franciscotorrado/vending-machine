package com.machines.vending.infrastructure.persistence.mappers;

import com.machines.vending.domain.models.Product;
import com.machines.vending.infrastructure.persistence.entities.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.machines.vending.utils.TestAmounts.FIVE;
import static com.machines.vending.utils.TestAmounts.TEN;
import static org.assertj.core.api.Assertions.assertThat;

class ProductMapperTest {

    private int id;
    private String productName;
    private int sellerId;
    private int amountAvailable;

    @BeforeEach
    void setUp() {
        id = new Random().nextInt();
        productName = "Mikel Lun";
        sellerId = TEN;
        amountAvailable = FIVE;
    }

    @Test
    void fromEntityToModel() {
        //given
        final ProductEntity productEntity = ProductEntity.builder().id(id).productName(productName).sellerId(sellerId)
                .amountAvailable(amountAvailable).build();
        //when
        final Product product = ProductMapper.fromEntity(productEntity).toModel();
        //then
        assertThat(product.getId()).isEqualTo(id);
        assertThat(product.getProductName()).isEqualTo(productName);
        assertThat(product.getSellerId()).isEqualTo(sellerId);
        assertThat(product.getAmountAvailable()).isEqualTo(amountAvailable);
    }

    @Test
    void fromModelToEntity() {
        //given
        final Product product = Product.builder().id(id).productName(productName).sellerId(sellerId)
                .amountAvailable(amountAvailable).build();
        //when
        final ProductEntity productEntity = ProductMapper.fromModel(product).toEntity();
        //then
        assertThat(productEntity.getId()).isEqualTo(id);
        assertThat(productEntity.getProductName()).isEqualTo(productName);
        assertThat(productEntity.getSellerId()).isEqualTo(sellerId);
        assertThat(productEntity.getAmountAvailable()).isEqualTo(amountAvailable);
    }
}
