package com.machines.vending.domain.models.deposits;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoinTest {

    @Test
    void getValuesByName() {
        assertThat(Coin.FIVE.getValue()).isEqualTo(5);
        assertThat(Coin.TEN.getValue()).isEqualTo(10);
    }

    @Test
    void getEnumByValue() {
        assertThat(Coin.of(5)).isEqualTo(Coin.FIVE);
        assertThat(Coin.of(10)).isEqualTo(Coin.TEN);
    }

}
