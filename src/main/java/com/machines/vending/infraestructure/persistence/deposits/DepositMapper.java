package com.machines.vending.infraestructure.persistence.deposits;

import com.machines.vending.domain.models.deposits.Deposit;
import com.machines.vending.infraestructure.persistence.mappers.EntityMapper;
import com.machines.vending.infraestructure.persistence.mappers.ModelMapper;

public final class DepositMapper {
    public static EntityMapper fromEntity(final DepositEntity depositEntity) {
        return () -> new Deposit(depositEntity.getBuyerId(), depositEntity.getAmount());
    }

    public static ModelMapper fromModel(final Deposit deposit) {
        return () -> new DepositEntity(deposit.getBuyerId(), deposit.getAmount());
    }
}
