package com.machines.vending.infraestructure.persistence.mappers;

import com.machines.vending.domain.models.deposits.Deposit;

public interface EntityMapper {
    Deposit toModel();
}
