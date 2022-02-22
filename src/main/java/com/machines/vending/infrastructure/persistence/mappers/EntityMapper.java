package com.machines.vending.infrastructure.persistence.mappers;

import com.machines.vending.domain.models.Model;

public interface EntityMapper<MODEL extends Model> {
    MODEL toModel();
}
