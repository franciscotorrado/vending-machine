package com.machines.vending.domain.models;

import com.machines.vending.domain.exceptions.NotEnoughDepositException;
import com.machines.vending.domain.models.Model;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Deposit extends Model {
    private Integer id;
    private int buyerId;
    private int amount;

    public void add(int amount) {
        this.amount += amount;
    }

    public void withdraw(final int amount) throws NotEnoughDepositException {
        validate(amount);
        this.amount -= amount;
    }

    public void reset() {
        this.amount = 0;
    }

    private void validate(final int amount) throws NotEnoughDepositException {
        if (amount > this.amount) {
            throw new NotEnoughDepositException();
        }
    }
}
