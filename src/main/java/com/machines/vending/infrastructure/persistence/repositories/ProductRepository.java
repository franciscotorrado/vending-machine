package com.machines.vending.infrastructure.persistence.repositories;

import com.machines.vending.infrastructure.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    @Modifying
    @Query(value = "UPDATE ProductEntity p SET p.amountAvailable = :amountAvailable WHERE p.id = :productId")
    void updateAmountAvailable(int productId,
                               int amountAvailable);

    @Modifying
    @Query(value = "DELETE FROM ProductEntity p WHERE p.id = :productId AND p.sellerId = :sellerId")
    void deleteByIdAndSellerId(int productId,
                               int sellerId);

}
