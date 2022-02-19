package com.machines.vending.domain.models.deposits;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum Coin {
    FIVE(5),
    TEN(10);

    private static final Map<Integer, Coin> values = new HashMap<>();

    static {
        Arrays.stream(Coin.values()).forEach(c -> values.put(c.getValue(), c));
    }

    private int value;

    static Coin of(final int value) {
        return values.get(value);
    }
}
