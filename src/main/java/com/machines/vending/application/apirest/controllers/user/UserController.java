package com.machines.vending.application.apirest.controllers.user;

import com.machines.vending.application.apirest.controllers.BaseController;
import com.machines.vending.domain.commands.user.CreateUserCommand;
import com.machines.vending.domain.commands.user.DeleteUserCommand;
import com.machines.vending.domain.commands.user.ReadUserCommand;
import com.machines.vending.domain.commands.user.UpdateUserCommand;
import com.machines.vending.domain.exceptions.session.NoActiveSessionException;
import com.machines.vending.domain.exceptions.user.CreateUserException;
import com.machines.vending.domain.exceptions.PositiveDepositAvailableException;
import com.machines.vending.domain.exceptions.role.InvalidRoleException;
import com.machines.vending.domain.exceptions.user.CreateUserWithGivenIdException;
import com.machines.vending.domain.exceptions.user.InvalidPasswordException;
import com.machines.vending.domain.exceptions.user.InvalidUsernameException;
import com.machines.vending.domain.exceptions.user.UserNotFoundException;
import com.machines.vending.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class UserController extends BaseController {
    private final CreateUserCommand createUserCommand;
    private DeleteUserCommand deleteUserCommand;
    private ReadUserCommand readUserCommand;
    private UpdateUserCommand updateUserCommand;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(CREATED)
    public void createUser(@RequestBody User user) throws CreateUserWithGivenIdException, InvalidPasswordException, InvalidRoleException, InvalidUsernameException, CreateUserException {
        createUserCommand.execute(user);
    }

    @DeleteMapping()
    @ResponseStatus(OK)
    public void deleteUser(@RequestHeader(TOKEN_KEY) String token) throws PositiveDepositAvailableException, NoActiveSessionException {
        final Integer userId = getUserInformationFromToken(token);
        deleteUserCommand.execute(User.builder().id(userId).build());
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(OK)
    public User readUser(@RequestHeader(TOKEN_KEY) String token) throws UserNotFoundException, NoActiveSessionException {
        final Integer userId = getUserInformationFromToken(token);
        return readUserCommand.execute(User.builder().id(userId).build());
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(OK)
    public void updateUser(@RequestHeader(TOKEN_KEY) String token,
                           @RequestBody User user) throws InvalidPasswordException, InvalidRoleException, InvalidUsernameException, NoActiveSessionException {
        final Integer userId = getUserInformationFromToken(token);
        updateUserCommand.execute(User.builder().id(userId).username(user.getUsername()).password(user.getPassword()).build());
    }

}
