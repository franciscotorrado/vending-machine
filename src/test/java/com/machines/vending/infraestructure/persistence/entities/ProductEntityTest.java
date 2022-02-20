package com.machines.vending.infraestructure.persistence.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.machines.vending.utils.TestAmounts.TEN;
import static com.machines.vending.utils.TestAmounts.THREE;
import static com.machines.vending.utils.TestAmounts.TWENTY;
import static org.assertj.core.api.Assertions.assertThat;

class ProductEntityTest {

    private int id;
    private String productName;
    private int cost;
    private int amountAvailable;
    private int sellerId;
    private ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        id = new Random().nextInt();
        productName = "Product#ID";
        sellerId = new Random().nextInt();
        amountAvailable = THREE;
        cost = TEN;
        productEntity = ProductEntity.builder().id(id).productName(productName)
                .sellerId(sellerId).cost(TEN).amountAvailable(amountAvailable).build();
    }

    @Test
    void getId() {
        assertThat(productEntity.getId()).isEqualTo(id);
    }

    @Test
    void getSellerId() {
        assertThat(productEntity.getSellerId()).isEqualTo(sellerId);
    }

    @Test
    void getCost() {
        assertThat(productEntity.getCost()).isEqualTo(cost);
    }

    @Test
    void getProductName() {
        assertThat(productEntity.getProductName()).isEqualTo(productName);
    }

    @Test
    void getAmountAvailable() {
        assertThat(productEntity.getAmountAvailable()).isEqualTo(amountAvailable);
    }


    @Test
    void setId() {
        // given
        final int newId = id + 1;
        // when
        productEntity.setId(newId);
        // then
        assertThat(productEntity.getId()).isEqualTo(newId);
    }

    @Test
    void setSellerId() {
        // given
        final int newSellerId = sellerId + 1;
        // when
        productEntity.setSellerId(newSellerId);
        // then
        assertThat(productEntity.getSellerId()).isEqualTo(newSellerId);
    }

    @Test
    void setCost() {
        // given
        // when
        productEntity.setCost(TWENTY);
        // then
        assertThat(productEntity.getCost()).isEqualTo(TWENTY);
    }

    @Test
    void setAmountAvailable() {
        // given
        // when
        productEntity.setAmountAvailable(TWENTY);
        // then
        assertThat(productEntity.getAmountAvailable()).isEqualTo(TWENTY);
    }


    @Test
    void setProductName() {
        // given
        final String newProductName = "New Product Name";
        // when
        productEntity.setProductName(newProductName);
        // then
        assertThat(productEntity.getProductName()).isEqualTo(newProductName);
    }

    @Test
    void constructor() {
        // given
        // when
        final ProductEntity product = new ProductEntity();
        // then
        assertThat(product.getId()).isNull();
        assertThat(product.getProductName()).isNull();
        assertThat(product.getSellerId()).isZero();
        assertThat(product.getAmountAvailable()).isZero();
        assertThat(product.getCost()).isZero();
    }


}
