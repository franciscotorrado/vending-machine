package com.machines.vending.domain.commands;

import com.machines.vending.domain.models.Deposit;

public interface ToDepositCommand {
    void to(Deposit deposit);
}
