package com.machines.vending.infraestructure.persistence.entities;

import com.machines.vending.domain.models.Deposit;
import com.machines.vending.infraestructure.persistence.mappers.EntityMapper;
import com.machines.vending.infraestructure.persistence.mappers.ModelMapper;

public final class DepositMapper {
    public static EntityMapper<Deposit> fromEntity(final DepositEntity depositEntity) {
        return () -> Deposit.builder()
                .id(depositEntity.getId())
                .buyerId(depositEntity.getBuyerId())
                .amount(depositEntity.getAmount())
                .build();
    }

    public static ModelMapper<DepositEntity> fromModel(final Deposit deposit) {
        return () -> DepositEntity.builder()
                .id(deposit.getId())
                .buyerId(deposit.getBuyerId())
                .amount(deposit.getAmount())
                .build();
    }
}
