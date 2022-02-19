package com.machines.vending.infraestructure.persistence.deposits;

import com.machines.vending.domain.models.Coin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class DepositEntityTest {

    private Integer buyerId;
    private DepositEntity depositEntity;
    private int id;
    private int ten;

    @BeforeEach
    void setUp() {
        ten = Coin.TEN.getValue();
        id = new Random().nextInt();
        buyerId = new Random().nextInt();
        depositEntity = DepositEntity.builder().id(id).buyerId(buyerId).amount(ten).build();
    }

    @Test
    void getId() {
        assertThat(depositEntity.getId()).isEqualTo(id);
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
    void setId() {
        // given
        final int newId = id + 1;
        // when
        depositEntity.setId(newId);
        // then
        assertThat(depositEntity.getId()).isEqualTo(newId);
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
        assertThat(deposit.getId()).isNull();
        assertThat(deposit.getBuyerId()).isZero();
        assertThat(deposit.getAmount()).isZero();
    }


}
