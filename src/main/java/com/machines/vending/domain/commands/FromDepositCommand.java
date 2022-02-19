package com.machines.vending.domain.commands;

import com.machines.vending.domain.exceptions.NotEnoughDepositException;
import com.machines.vending.domain.models.deposits.Deposit;

public interface FromDepositCommand {
    void from(Deposit deposit) throws NotEnoughDepositException;
}
