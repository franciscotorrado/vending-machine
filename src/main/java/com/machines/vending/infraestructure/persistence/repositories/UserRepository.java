package com.machines.vending.infraestructure.persistence.repositories;

import com.machines.vending.infraestructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
