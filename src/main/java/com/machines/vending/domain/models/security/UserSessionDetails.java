package com.machines.vending.domain.models.security;

import com.machines.vending.domain.models.Role;
import lombok.Data;

@Data
public class UserSessionDetails {
    private final Integer userId;
    private final Role role;
}
