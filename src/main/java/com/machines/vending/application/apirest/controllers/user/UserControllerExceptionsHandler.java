package com.machines.vending.application.apirest.controllers.user;

import com.machines.vending.domain.exceptions.PositiveDepositAvailableException;
import com.machines.vending.domain.exceptions.role.InvalidRoleException;
import com.machines.vending.domain.exceptions.user.CreateUserException;
import com.machines.vending.domain.exceptions.user.CreateUserWithGivenIdException;
import com.machines.vending.domain.exceptions.user.InvalidPasswordException;
import com.machines.vending.domain.exceptions.user.InvalidUsernameException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@AllArgsConstructor
@RestControllerAdvice("com.machines.vending.application.apirest.controllers.user")
public class UserControllerExceptionsHandler {
    @ExceptionHandler({
            InvalidUsernameException.class,
            InvalidPasswordException.class,
            InvalidRoleException.class,
            CreateUserWithGivenIdException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserExceptions(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler({
            PositiveDepositAvailableException.class,
            CreateUserException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserConflictExceptions(Exception e) {
        return e.getMessage();
    }
}
