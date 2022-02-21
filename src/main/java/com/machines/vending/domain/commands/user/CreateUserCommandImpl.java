package com.machines.vending.domain.commands.user;

import com.machines.vending.domain.exceptions.role.InvalidRoleException;
import com.machines.vending.domain.exceptions.user.CreateUserWithGivenIdException;
import com.machines.vending.domain.exceptions.user.InvalidPasswordException;
import com.machines.vending.domain.exceptions.user.InvalidUsernameException;
import com.machines.vending.domain.models.Role;
import com.machines.vending.domain.models.User;
import com.machines.vending.infraestructure.persistence.entities.UserEntity;
import com.machines.vending.infraestructure.persistence.mappers.UserMapper;
import com.machines.vending.infraestructure.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;

import static java.util.Objects.isNull;

@AllArgsConstructor
public class CreateUserCommandImpl implements CreateUserCommand {
    private final UserRepository userRepository;

    @Override
    public User execute(final User user) throws InvalidPasswordException, InvalidUsernameException, InvalidRoleException, CreateUserWithGivenIdException {
        checkIsNew(user);
        User.validate(user);
        Role.validate(user.getRole());

        final UserEntity userEntity = UserMapper.fromModel(user).toEntity();

        final UserEntity createdUser = userRepository.save(userEntity);

        return User.builder().id(createdUser.getId()).build();
    }

    private void checkIsNew(final User user) throws CreateUserWithGivenIdException {
        if (!isNull(user.getId())) {
            throw new CreateUserWithGivenIdException();
        }
    }
}
