package com.machines.vending.infraestructure.persistence.mappers;

import com.machines.vending.infraestructure.persistence.deposits.DepositEntity;

public interface ModelMapper {
    DepositEntity toEntity();
}
