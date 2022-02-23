package com.machines.vending.application.apirest.controllers;

import com.machines.vending.infrastructure.session.TokenServer;


public class BaseController {
    protected static final String TOKEN_KEY = "Authorization";

    protected Integer[] getUserInformationFromToken(final String token) {
        final Integer[] userId = new Integer[1];
        TokenServer.getUserId(token).ifPresent(id -> userId[0] = id);
        return userId;
    }
}
