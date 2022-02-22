package com.machines.vending.application.apirest.controllers.user;

import com.machines.vending.domain.exceptions.role.InvalidRoleException;
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
            InvalidRoleException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidCoinException(Exception e) {
        return e.getMessage();
    }
}
