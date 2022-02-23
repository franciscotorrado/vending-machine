package com.machines.vending.domain.commands.user;

import com.machines.vending.domain.exceptions.role.InvalidRoleException;
import com.machines.vending.domain.exceptions.user.InvalidPasswordException;
import com.machines.vending.domain.exceptions.user.InvalidUsernameException;
import com.machines.vending.domain.models.User;
import com.machines.vending.infrastructure.persistence.entities.UserEntity;
import com.machines.vending.infrastructure.persistence.mappers.UserMapper;
import com.machines.vending.infrastructure.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UpdateUserCommandImpl implements UpdateUserCommand {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void execute(final User user) throws InvalidPasswordException, InvalidUsernameException, InvalidRoleException {
        User.validate(user);

        final UserEntity userEntity = UserMapper.fromModel(user).toEntity();

        userRepository.update(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword());
    }
}
