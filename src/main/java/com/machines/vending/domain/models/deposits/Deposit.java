package com.machines.vending.domain.models.deposits;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Deposit {
    private Integer buyerId;
    private int value;

    Deposit(final Integer buyerId) {
        this.buyerId = buyerId;
        reset();
    }

    public void add(int value) {
        this.value += value;
    }

    public void reset() {
        this.value = 0;
    }
}
