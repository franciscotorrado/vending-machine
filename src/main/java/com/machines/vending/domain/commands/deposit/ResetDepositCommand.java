package com.machines.vending.domain.commands.deposit;

import com.machines.vending.domain.models.Deposit;

public interface ResetDepositCommand {
    void reset(final Deposit deposit);
}
