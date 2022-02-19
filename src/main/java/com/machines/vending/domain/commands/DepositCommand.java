package com.machines.vending.domain.commands;

import com.machines.vending.domain.models.deposits.Deposit;

public interface DepositCommand {
    void to(Deposit deposit);
}
