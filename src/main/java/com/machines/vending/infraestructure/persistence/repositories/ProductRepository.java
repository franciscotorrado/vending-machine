package com.machines.vending.infraestructure.persistence.repositories;

import com.machines.vending.infraestructure.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    @Modifying
    @Query(value = "UPDATE Product p SET p.amountAvailable = :amountAvailable WHERE p.id = :productId")
    void updateAmountAvailable(int productId,
                               int amountAvailable);
}
