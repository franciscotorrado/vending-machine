package com.machines.vending.infraestructure.persistence.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.machines.vending.utils.TestAmounts.TEN;
import static com.machines.vending.utils.TestAmounts.TWENTY;
import static org.assertj.core.api.Assertions.assertThat;

class DepositEntityTest {

    private Integer buyerId;
    private DepositEntity depositEntity;
    private int id;

    @BeforeEach
    void setUp() {
        id = new Random().nextInt();
        buyerId = new Random().nextInt();
        depositEntity = DepositEntity.builder().id(id).buyerId(buyerId).amount(TEN).build();
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
        assertThat(depositEntity.getAmount()).isEqualTo(TEN);
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
        // when
        depositEntity.setAmount(TWENTY);
        // then
        assertThat(depositEntity.getAmount()).isEqualTo(TWENTY);
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
