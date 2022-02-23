package com.machines.vending.application.apirest.controllers;

import com.machines.vending.domain.exceptions.security.UserAccessDeniedException;
import com.machines.vending.domain.exceptions.session.NoActiveSessionException;
import com.machines.vending.domain.models.Role;
import com.machines.vending.domain.models.security.UserSessionDetails;
import com.machines.vending.infrastructure.session.TokenServer;

import java.util.Arrays;


public class BaseController {
    protected static final String TOKEN_KEY = "Authorization";

    protected Integer checkRights(final String token,
                                  final Role... roles) throws NoActiveSessionException, UserAccessDeniedException {
        final UserSessionDetails userSessionDetails = TokenServer.getUserSessionDetails(token.replace("Bearer ", "")).orElseThrow(NoActiveSessionException::new);
        if (Arrays.stream(roles).anyMatch(r -> r.equals(userSessionDetails.getRole()))) {
            return userSessionDetails.getUserId();
        } else {
            throw new UserAccessDeniedException();
        }
    }
}
