package com.machines.vending.domain.commands;

import com.machines.vending.domain.models.deposits.Deposit;

public interface ResetDepositCommand {
    void reset(final Deposit deposit);
}
