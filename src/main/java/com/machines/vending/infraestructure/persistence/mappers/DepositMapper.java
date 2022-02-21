package com.machines.vending.infraestructure.persistence.mappers;

import com.machines.vending.domain.models.Deposit;
import com.machines.vending.infraestructure.persistence.entities.DepositEntity;

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
