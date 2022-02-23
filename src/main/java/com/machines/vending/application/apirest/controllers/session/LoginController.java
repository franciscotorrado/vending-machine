package com.machines.vending.application.apirest.controllers.session;

import com.machines.vending.application.apirest.controllers.BaseController;
import com.machines.vending.domain.commands.session.LoginCommand;
import com.machines.vending.domain.commands.session.LogoutCommand;
import com.machines.vending.domain.models.security.LoginRequest;
import com.machines.vending.domain.models.security.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/")
public class LoginController extends BaseController {

    private final LoginCommand loginCommand;
    private final LogoutCommand logoutCommand;

    @PostMapping(value = "/login",
            consumes = "application/json",
            produces = "application/json")
    @ResponseStatus(OK)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
        final String username = loginRequest.getUsername();
        final String password = loginRequest.getPassword();
        String token = loginCommand.execute(username, password);
        return new LoginResponse(token);
    }

    @GetMapping("/logout/all")
    @ResponseStatus(OK)
    public void logout(@RequestHeader(TOKEN_KEY) String token) throws Exception {
        final Integer userId = getUserInformationFromToken(token);
        logoutCommand.execute(userId);
    }
}
