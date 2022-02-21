package com.machines.vending.domain.models;

import com.machines.vending.domain.exceptions.coin.InvalidCoinException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void shouldThrowInvalidCoinException_whenAnInvalidCoinIsReceived() {
        // given
        final int three = 3;

        // when
        // then
        assertThrows(InvalidCoinException.class, () -> Coin.validate(three));
    }
}
