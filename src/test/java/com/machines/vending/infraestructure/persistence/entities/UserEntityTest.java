package com.machines.vending.infraestructure.persistence.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class UserEntityTest {

    private UserEntity userEntity;
    private int id;
    private String username;
    private String password;
    private String role;

    @BeforeEach
    void setUp() {
        id = new Random().nextInt();
        username = "MariaTod";
        password = "e344rrRR$";
        role = "ROLE";
        userEntity = UserEntity.builder().id(id).username(username).password(password).role(role).build();
    }

    @Test
    void getId() {
        assertThat(userEntity.getId()).isEqualTo(id);
    }

    @Test
    void getUsername() {
        assertThat(userEntity.getUsername()).isEqualTo(username);
    }

    @Test
    void getPassword() {
        assertThat(userEntity.getPassword()).isEqualTo(password);
    }

    @Test
    void getRole() {
        assertThat(userEntity.getRole()).isEqualTo(role);
    }

    @Test
    void setId() {
        // given
        final int newId = id + 1;
        // when
        userEntity.setId(newId);
        // then
        assertThat(userEntity.getId()).isEqualTo(newId);
    }

    @Test
    void setUsername() {
        // given
        final String newUsername = "DavidCore";
        // when
        userEntity.setUsername(newUsername);
        // then
        assertThat(userEntity.getUsername()).isEqualTo(newUsername);
    }

    @Test
    void setPassword() {
        // given
        final String newPassword = "aaa333@TVB";
        // when
        userEntity.setPassword(newPassword);
        // then
        assertThat(userEntity.getPassword()).isEqualTo(newPassword);
    }

    @Test
    void setRole() {
        // given
        final String newRole = "NEW_ROLE";
        // when
        userEntity.setRole(newRole);
        // then
        assertThat(userEntity.getRole()).isEqualTo(newRole);
    }


    @Test
    void constructor() {
        // given
        // when
        final UserEntity user = new UserEntity();
        // then
        assertThat(user.getId()).isNull();
        assertThat(user.getUsername()).isNull();
        assertThat(user.getPassword()).isNull();
        assertThat(user.getRole()).isNull();
    }


}
