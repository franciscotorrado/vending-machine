package com.machines.vending.application.apirest.controllers.session;

import com.machines.vending.domain.commands.session.LoginCommand;
import com.machines.vending.domain.models.security.AuthenticationRequest;
import com.machines.vending.domain.models.security.AuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/",
        consumes = "application/json",
        produces = "application/json")
public class LoginController {

    private final LoginCommand loginCommand;

    @PostMapping("/login")
    public AuthenticationResponse createToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        final String username = authenticationRequest.getUsername();
        final String password = authenticationRequest.getPassword();
        String token = loginCommand.login(username, password);
        return new AuthenticationResponse(token);
    }
}
