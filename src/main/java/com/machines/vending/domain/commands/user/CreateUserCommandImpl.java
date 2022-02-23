package com.machines.vending.domain.commands.user;

import com.machines.vending.domain.exceptions.user.CreateUserException;
import com.machines.vending.domain.exceptions.role.InvalidRoleException;
import com.machines.vending.domain.exceptions.user.CreateUserWithGivenIdException;
import com.machines.vending.domain.exceptions.user.InvalidPasswordException;
import com.machines.vending.domain.exceptions.user.InvalidUsernameException;
import com.machines.vending.domain.models.Role;
import com.machines.vending.domain.models.User;
import com.machines.vending.infrastructure.persistence.entities.UserEntity;
import com.machines.vending.infrastructure.persistence.mappers.UserMapper;
import com.machines.vending.infrastructure.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class CreateUserCommandImpl implements CreateUserCommand {
    private final UserRepository userRepository;

    @Override
    public void execute(final User user) throws InvalidPasswordException, InvalidUsernameException, InvalidRoleException, CreateUserWithGivenIdException, CreateUserException {
        checkIsNew(user);
        User.validate(user);
        Role.validate(user.getRole());

        final UserEntity userEntity = UserMapper.fromModel(user).toEntity();

        try {
            userRepository.save(userEntity);
        } catch (Exception e) {
            throw new CreateUserException();
        }
    }

    private void checkIsNew(final User user) throws CreateUserWithGivenIdException {
        if (!isNull(user.getId())) {
            throw new CreateUserWithGivenIdException();
        }
    }
}
