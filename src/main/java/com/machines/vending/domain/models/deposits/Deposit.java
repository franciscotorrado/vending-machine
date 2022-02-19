package com.machines.vending.domain.models.deposits;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Deposit {
    private Integer buyerId;
    private int amount;

    Deposit(final Integer buyerId) {
        this.buyerId = buyerId;
        reset();
    }

    public void add(int amount) {
        this.amount += amount;
    }

    public void remove(final int amount) {
        this.amount -= amount;
    }

    public void reset() {
        this.amount = 0;
    }
}
