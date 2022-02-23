package com.machines.vending.application.apirest.controllers.session;

import com.machines.vending.domain.exceptions.session.ConcurrentSessionsException;
import com.machines.vending.domain.exceptions.security.InvalidUsernameOrPasswordException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@AllArgsConstructor
@RestControllerAdvice("com.machines.vending.application.apirest.controllers.session")
public class LoginControllerExceptionsHandler {
    @ExceptionHandler({InvalidUsernameOrPasswordException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleLoginExceptions(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler({ConcurrentSessionsException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleConcurrentSessionExceptions(Exception e) {
        return e.getMessage();
    }
}
