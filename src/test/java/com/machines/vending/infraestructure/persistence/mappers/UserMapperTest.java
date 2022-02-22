package com.machines.vending.infraestructure.persistence.mappers;

import com.machines.vending.domain.models.User;
import com.machines.vending.infraestructure.persistence.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private int id;
    private String username;
    private String password;
    private String role;

    @BeforeEach
    void setUp() {
        id = new Random().nextInt();
        username = "MikelLun";
        password = "wer34$3sdfDD";
        role = "ROLE";
    }

    @Test
    void fromEntityToModel() {
        //given
        final UserEntity userEntity = UserEntity.builder().id(id).username(username).password(password).role(role).build();
        //when
        final User user = UserMapper.fromEntity(userEntity).toModel();
        //then
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getRole()).isEqualTo(role);
    }

    @Test
    void fromModelToEntity() {
        //given
        final User user = User.builder().id(id).username(username).password(password).role(role).build();
        //when
        final UserEntity userEntity = UserMapper.fromModel(user).toEntity();
        //then
        assertThat(userEntity.getId()).isEqualTo(id);
        assertThat(userEntity.getUsername()).isEqualTo(username);
        assertThat(userEntity.getPassword()).isEqualTo(password);
        assertThat(userEntity.getRole()).isEqualTo(role);
    }
}
