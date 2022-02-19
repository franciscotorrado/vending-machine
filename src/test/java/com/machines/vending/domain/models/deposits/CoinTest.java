package com.machines.vending.domain.models.deposits;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoinTest {

    @Test
    void getValuesByName() {
        assertThat(Coin.FIVE.getValue()).isEqualTo(5);
        assertThat(Coin.TEN.getValue()).isEqualTo(10);
        assertThat(Coin.TWENTY.getValue()).isEqualTo(20);
        assertThat(Coin.FIFTY.getValue()).isEqualTo(50);
        assertThat(Coin.HUNDRED.getValue()).isEqualTo(100);
    }

    @Test
    void getEnumByValue() {
        assertThat(Coin.of(5)).isEqualTo(Coin.FIVE);
        assertThat(Coin.of(10)).isEqualTo(Coin.TEN);
        assertThat(Coin.of(20)).isEqualTo(Coin.TWENTY);
        assertThat(Coin.of(50)).isEqualTo(Coin.FIFTY);
        assertThat(Coin.of(100)).isEqualTo(Coin.HUNDRED);
    }

}
