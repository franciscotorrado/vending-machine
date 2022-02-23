package com.machines.vending.application.apirest.controllers.user;

import com.machines.vending.domain.commands.user.CreateUserCommand;
import com.machines.vending.domain.commands.user.DeleteUserCommand;
import com.machines.vending.domain.exceptions.PositiveDepositAvailableException;
import com.machines.vending.domain.exceptions.role.InvalidRoleException;
import com.machines.vending.domain.exceptions.user.CreateUserWithGivenIdException;
import com.machines.vending.domain.exceptions.user.InvalidPasswordException;
import com.machines.vending.domain.exceptions.user.InvalidUsernameException;
import com.machines.vending.domain.models.User;
import com.machines.vending.infrastructure.session.TokenServer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static final String TOKEN_KEY = "Authorization";
    private final CreateUserCommand createUserCommand;
    private DeleteUserCommand deleteUserCommand;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(CREATED)
    public void createUser(@RequestBody User user) throws CreateUserWithGivenIdException, InvalidPasswordException, InvalidRoleException, InvalidUsernameException {
        createUserCommand.execute(user);
    }

    @DeleteMapping()
    @ResponseStatus(OK)
    public void deleteUser(@RequestHeader(TOKEN_KEY) String token) throws PositiveDepositAvailableException {
        final Integer[] userId = new Integer[1];
        TokenServer.getUserId(token).ifPresent(id -> userId[0] = id);
        deleteUserCommand.execute(User.builder().id(userId[0]).build());
    }
}
