package com.machines.vending.domain.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseTest {

    private int productId;
    private int amount;

    @BeforeEach
    void setUp() {
        productId = 12312;
        amount = 4;
    }

    @Test
    void getProductId() {
        //given
        final Purchase purchase = Purchase.builder().productId(productId).build();
        //when
        //then
        assertThat(purchase.getProductId()).isEqualTo(productId);
        assertThat(purchase.getAmount()).isZero();
    }

    @Test
    void getAmount() {
        //given
        final Purchase purchase = Purchase.builder().amount(amount).build();
        //when
        //then
        assertThat(purchase.getProductId()).isZero();
        assertThat(purchase.getAmount()).isEqualTo(amount);
    }
}
