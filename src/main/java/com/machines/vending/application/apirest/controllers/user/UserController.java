package com.machines.vending.application.apirest.controllers.user;

import com.machines.vending.domain.commands.user.CreateUserCommand;
import com.machines.vending.domain.commands.user.DeleteUserCommand;
import com.machines.vending.domain.exceptions.role.InvalidRoleException;
import com.machines.vending.domain.exceptions.user.CreateUserWithGivenIdException;
import com.machines.vending.domain.exceptions.user.InvalidPasswordException;
import com.machines.vending.domain.exceptions.user.InvalidUsernameException;
import com.machines.vending.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/user",
        consumes = "application/json",
        produces = "application/json")
public class UserController {
    private final CreateUserCommand createUserCommand;
    private DeleteUserCommand deleteUserCommand;

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) throws CreateUserWithGivenIdException, InvalidPasswordException, InvalidRoleException, InvalidUsernameException {
        return new ResponseEntity<>(createUserCommand.execute(user), HttpStatus.CREATED);
    }

    @DeleteMapping()
    @ResponseStatus(OK)
    public ResponseEntity<Void> deleteUser(Authentication authentication) {
        final User user = (User) authentication.getPrincipal();
        deleteUserCommand.execute(User.builder().id(user.getId()).build());
        return ResponseEntity.ok().build();
    }
}
