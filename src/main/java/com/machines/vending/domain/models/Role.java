package com.machines.vending.domain.models;

import com.machines.vending.domain.exceptions.InvalidRoleException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public enum Role {
    SELLER, BUYER;

    private static final Map<String, Role> values = new HashMap<>();

    static {
        Arrays.stream(Role.values()).forEach(r -> values.put(r.name(), r));
    }

    public static Role of(final String name) {
        return values.get(name.toUpperCase());
    }

    public static void validate(final String name) throws InvalidRoleException {
        if (isNull(Role.of(name.toUpperCase()))) {
            throw new InvalidRoleException();
        }
    }
}
