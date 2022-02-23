package com.machines.vending.domain.models;

import com.machines.vending.domain.exceptions.deposit.NotEnoughDepositException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Deposit extends Model {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
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
