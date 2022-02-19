package com.machines.vending.infraestructure.persistence.deposits;

import com.machines.vending.domain.models.deposits.Deposit;
import com.machines.vending.infraestructure.persistence.mappers.EntityMapper;
import com.machines.vending.infraestructure.persistence.mappers.ModelMapper;

public final class DepositMapper {
    public static EntityMapper<Deposit> fromEntity(final DepositEntity depositEntity) {
        return () -> new Deposit(depositEntity.getId(), depositEntity.getBuyerId(), depositEntity.getAmount());
    }

    public static ModelMapper<DepositEntity> fromModel(final Deposit deposit) {
        return () -> DepositEntity.builder()
                .id(deposit.getId())
                .buyerId(deposit.getBuyerId())
                .amount(deposit.getAmount())
                .build();
    }
}
