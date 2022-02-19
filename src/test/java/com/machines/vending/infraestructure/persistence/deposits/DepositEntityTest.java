package com.machines.vending.infraestructure.persistence.deposits;

import com.machines.vending.domain.models.Coin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class DepositEntityTest {

    private Integer buyerId;
    private DepositEntity depositEntity;
    private int ten;

    @BeforeEach
    void setUp() {
        ten = Coin.TEN.getValue();
        buyerId = new Random().nextInt();
        depositEntity = new DepositEntity(buyerId, ten);
    }

    @Test
    void getBuyerId() {
        assertThat(depositEntity.getBuyerId()).isEqualTo(buyerId);
    }

    @Test
    void getValue() {
        assertThat(depositEntity.getAmount()).isEqualTo(ten);
    }

    @Test
    void setBuyerId() {
        // given
        final int newBuyerId = buyerId + 1;
        // when
        depositEntity.setBuyerId(newBuyerId);
        // then
        assertThat(depositEntity.getBuyerId()).isEqualTo(newBuyerId);
    }

    @Test
    void setValue() {
        // given
        final int twenty = 20;
        // when
        depositEntity.setAmount(twenty);
        // then
        assertThat(depositEntity.getAmount()).isEqualTo(twenty);
    }

    @Test
    void constructor() {
        // given
        // when
        final DepositEntity deposit = new DepositEntity();
        // then
        assertThat(deposit.getBuyerId()).isNull();
        assertThat(deposit.getAmount()).isZero();
    }


}
