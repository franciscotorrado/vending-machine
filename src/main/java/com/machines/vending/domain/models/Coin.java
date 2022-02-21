package com.machines.vending.domain.models;

import com.machines.vending.domain.exceptions.coin.InvalidCoinException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Getter
public enum Coin {
    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100);

    private static final Map<Integer, Coin> values = new HashMap<>();

    static {
        Arrays.stream(Coin.values()).forEach(c -> values.put(c.getValue(), c));
    }

    private int value;

    public static Coin of(final int value) {
        return values.get(value);
    }

    public static void validate(final int value) throws InvalidCoinException {
        if (isNull(Coin.of(value))) {
            throw new InvalidCoinException();
        }
    }
}
