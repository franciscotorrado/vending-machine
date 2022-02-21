package com.machines.vending.domain.commands.deposit;

import com.machines.vending.domain.exceptions.deposit.NotEnoughDepositException;
import com.machines.vending.domain.models.Deposit;

public interface FromDepositCommand {
    void from(Deposit deposit) throws NotEnoughDepositException;
}
