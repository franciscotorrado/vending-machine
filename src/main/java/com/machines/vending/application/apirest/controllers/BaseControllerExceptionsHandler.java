package com.machines.vending.application.apirest.controllers;

import com.machines.vending.domain.exceptions.security.UserAccessDeniedException;
import com.machines.vending.domain.exceptions.session.NoActiveSessionException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@AllArgsConstructor
@RestControllerAdvice
public class BaseControllerExceptionsHandler {
    @ExceptionHandler({
            NoActiveSessionException.class,
            UserAccessDeniedException.class
    })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleConflictExceptions(Exception e) {
        return e.getMessage();
    }
}
