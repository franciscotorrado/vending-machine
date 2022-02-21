package com.machines.vending.infraestructure.persistence.repositories;

import com.machines.vending.infraestructure.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
