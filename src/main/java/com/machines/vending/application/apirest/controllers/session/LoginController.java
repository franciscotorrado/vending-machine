package com.machines.vending.application.apirest.controllers.session;

import com.machines.vending.domain.commands.session.LoginCommand;
import com.machines.vending.domain.exceptions.session.ConcurrentSessionsException;
import com.machines.vending.domain.exceptions.session.InvalidUsernameOrPassword;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/")
public class LoginController {

    private final LoginCommand loginCommand;

    @PostMapping("/login")
    public String login(@RequestParam(value = "username") final String username,
                        @RequestParam(value = "password") final String password) throws InvalidUsernameOrPassword, ConcurrentSessionsException {
        return loginCommand.login(username, password);
    }
}
