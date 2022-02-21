package com.machines.vending.domain.models;

import com.machines.vending.domain.exceptions.product.NotEnoughProductAmountAvailableException;
import com.machines.vending.domain.exceptions.product.NotValidProductCostException;
import com.machines.vending.domain.exceptions.product.NotValidProductNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.machines.vending.utils.TestAmounts.FIFTEEN;
import static com.machines.vending.utils.TestAmounts.FIVE;
import static com.machines.vending.utils.TestAmounts.TEN;
import static com.machines.vending.utils.TestAmounts.THREE;
import static com.machines.vending.utils.TestAmounts.TWENTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductTest {

    private int id;
    private String productName;

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

    @Test
    void shouldThrowsNotValidCostException_whenCostIsNotMultipleOfFive() {
        // given
        final Product product = Product.builder().cost(THREE).build();

        // when
        // then
        assertThrows(NotValidProductCostException.class, product::validate);
    }

    @Test
    void shouldThrowsNotValidNameException_whenNameIsNullOrEmpty() {
        // given
        final Product product = Product.builder().productName(null).build();

        // when
        // then
        assertThrows(NotValidProductNameException.class, product::validate);
    }

    @Test
    void shouldUpdateAvailableAmount() throws NotEnoughProductAmountAvailableException {
        // given
        final Product product = Product.builder().amountAvailable(FIFTEEN).build();

        // when
        product.reduceAvailableAmount(FIVE);

        // then
        assertThat(product.getAmountAvailable()).isEqualTo(TEN);
    }

    @Test
    void shouldThrowsNotEnoughProductAmountAvailableException_whenAmountToReduceIsBiggerThanAvailable() {
        // given
        final Product product = Product.builder().amountAvailable(TEN).build();

        // when
        // then
        assertThrows(NotEnoughProductAmountAvailableException.class, () -> product.reduceAvailableAmount(TWENTY));
    }
}
