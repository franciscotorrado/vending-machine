package com.machines.vending.application.apirest.controllers;

import com.machines.vending.domain.exceptions.session.NoActiveSessionException;
import com.machines.vending.infrastructure.session.TokenServer;


public class BaseController {
    protected static final String TOKEN_KEY = "Authorization";

    protected Integer getUserInformationFromToken(final String token) throws NoActiveSessionException {
        return TokenServer.getUserId(token).orElseThrow(NoActiveSessionException::new);
    }
}
