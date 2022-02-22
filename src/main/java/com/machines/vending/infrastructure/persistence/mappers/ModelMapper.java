package com.machines.vending.infrastructure.persistence.mappers;

import com.machines.vending.infrastructure.persistence.entities.DBEntity;

public interface ModelMapper<ENTITY extends DBEntity> {
    ENTITY toEntity();
}
