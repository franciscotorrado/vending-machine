package com.machines.vending.domain.models.deposits;

import com.machines.vending.application.exceptions.NotEnoughDepositException;
import com.machines.vending.domain.models.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Deposit extends Model {
    private Integer id;
    private int buyerId;
    private int amount;

    Deposit(final int buyerId) {
        this.buyerId = buyerId;
        reset();
    }

    public void add(int amount) {
        this.amount += amount;
    }

    public void remove(final int amount) throws NotEnoughDepositException {
        validate(amount);
        this.amount -= amount;
    }

    public void reset() {
        this.amount = 0;
    }

    private void validate(final int amount) throws NotEnoughDepositException {
        if(amount > this.amount) {
            throw new NotEnoughDepositException();
        }
    }
}
