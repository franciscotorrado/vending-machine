package com.machines.vending.infrastructure.persistence.repositories;

import com.machines.vending.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE UserEntity u SET u.username = :username, u.password = :password WHERE u.id = :id")
    void update(@Param("id") int id,
                @Param("username") String username,
                @Param("password") String password);

    void deleteByIdAndUsernameAndPassword(int id,
                                          String username,
                                          String password);
}
