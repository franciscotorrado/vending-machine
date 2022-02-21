package com.machines.vending.domain.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseTest {

    private int productId;
    private int buyerId;
    private int amount;

    @BeforeEach
    void setUp() {
        productId = 12312;
        buyerId = 333;
        amount = 4;
    }

    @Test
    void getProductId() {
        //given
        final Purchase purchase = Purchase.builder().productId(productId).build();
        //when
        //then
        assertThat(purchase.getProductId()).isEqualTo(productId);
        assertThat(purchase.getBuyerId()).isZero();
        assertThat(purchase.getAmount()).isZero();
    }

    @Test
    void getBuyerId() {
        //given
        final Purchase purchase = Purchase.builder().buyerId(buyerId).build();
        //when
        //then
        assertThat(purchase.getProductId()).isZero();
        assertThat(purchase.getBuyerId()).isEqualTo(buyerId);
        assertThat(purchase.getAmount()).isZero();
    }

    @Test
    void getAmount() {
        //given
        final Purchase purchase = Purchase.builder().amount(amount).build();
        //when
        //then
        assertThat(purchase.getProductId()).isZero();
        assertThat(purchase.getBuyerId()).isZero();
        assertThat(purchase.getAmount()).isEqualTo(amount);
    }
}
