package com.machines.vending.domain.models;

import com.machines.vending.domain.exceptions.NotEnoughDepositException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.machines.vending.utils.TestAmounts.FIFTEEN;
import static com.machines.vending.utils.TestAmounts.FIVE;
import static com.machines.vending.utils.TestAmounts.TEN;
import static com.machines.vending.utils.TestAmounts.TWENTY;
import static com.machines.vending.utils.TestAmounts.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductTest {

    private int id;
    private String productName;
    private int cost;
    private int amountAvailable;
    private int sellerId;

    @BeforeEach
    void setUp() {
        id = new Random().nextInt();
        productName = "Product#" + id;
    }

    @Test
    void shouldCreateMinimalProduct() {
        // given
        // when
        final Product product = Product.builder().id(id).productName(productName).cost(FIVE).build();

        // then
        assertThat(product.getId()).isEqualTo(id);
        assertThat(product.getProductName()).isEqualTo(productName);
        assertThat(product.getCost()).isEqualTo(FIVE);
        assertThat(product.getAmountAvailable()).isZero();
        assertThat(product.getSellerId()).isZero();
    }
}
