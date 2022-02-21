package com.machines.vending.infraestructure.persistence.mappers;

import com.machines.vending.domain.models.User;
import com.machines.vending.infraestructure.persistence.entities.UserEntity;

public final class UserMapper {
    public static EntityMapper<User> fromEntity(final UserEntity userEntity) {
        return () -> User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .role(userEntity.getRole())
                .build();
    }

    public static ModelMapper<UserEntity> fromModel(final User user) {
        return () -> UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
