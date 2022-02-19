package com.machines.vending.infraestructure.persistence.deposits;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<DepositEntity, Integer> {

}
