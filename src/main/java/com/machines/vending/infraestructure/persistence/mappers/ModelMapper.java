package com.machines.vending.infraestructure.persistence.mappers;

import com.machines.vending.infraestructure.persistence.entities.DBEntity;

public interface ModelMapper<ENTITY extends DBEntity> {
    ENTITY toEntity();
}
