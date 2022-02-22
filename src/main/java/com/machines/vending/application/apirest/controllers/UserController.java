package com.machines.vending.application.apirest.controllers;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("/user")
@AllArgsConstructor
public class UserController {
    private final CreateUserCommand createUserCommand;
    private DeleteUserCommand deleteUserCommand;

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) throws CreateUserWithGivenIdException, InvalidPasswordException, InvalidRoleException, InvalidUsernameException {
        return new ResponseEntity<>(createUserCommand.execute(user), HttpStatus.CREATED);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteUser() {
        final Integer userId = 0;
        deleteUserCommand.execute(User.builder().id(userId).build());
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request,
                                       HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return ResponseEntity.ok().build();
    }
}
