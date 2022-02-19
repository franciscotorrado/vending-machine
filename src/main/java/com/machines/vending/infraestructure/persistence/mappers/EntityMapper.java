package com.machines.vending.infraestructure.persistence.mappers;

import com.machines.vending.domain.models.Model;

public interface EntityMapper<MODEL extends Model> {
    MODEL toModel();
}
