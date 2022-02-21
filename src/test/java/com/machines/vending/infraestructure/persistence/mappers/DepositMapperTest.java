package com.machines.vending.infraestructure.persistence.mappers;

import com.machines.vending.domain.models.Deposit;
import com.machines.vending.infraestructure.persistence.entities.DepositEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.machines.vending.utils.TestAmounts.TEN;
import static org.assertj.core.api.Assertions.assertThat;

class DepositMapperTest {

    private int id;
    private int buyerId;
    private int amount;

    @BeforeEach
    void setUp() {
        id = new Random().nextInt();
        buyerId = new Random().nextInt();
        amount = TEN;
    }

    @Test
    void fromEntityToModel() {
        //given
        final DepositEntity depositEntity = DepositEntity.builder().id(id).buyerId(buyerId).amount(amount).build();
        //when
        final Deposit deposit = DepositMapper.fromEntity(depositEntity).toModel();
        //then
        assertThat(deposit.getId()).isEqualTo(id);
        assertThat(deposit.getBuyerId()).isEqualTo(buyerId);
        assertThat(deposit.getAmount()).isEqualTo(amount);
    }

    @Test
    void fromModelToEntity() {
        //given
        final Deposit deposit = Deposit.builder().id(id).buyerId(buyerId).amount(amount).build();
        //when
        final DepositEntity depositEntity = DepositMapper.fromModel(deposit).toEntity();
        //then
        assertThat(depositEntity.getId()).isEqualTo(id);
        assertThat(depositEntity.getBuyerId()).isEqualTo(buyerId);
        assertThat(depositEntity.getAmount()).isEqualTo(amount);
    }
}
