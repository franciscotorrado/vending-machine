package com.machines.vending.domain.commands.deposit;

import com.machines.vending.domain.commands.deposit.FromDepositCommand;

public interface WithdrawFromDepositCommand {
    FromDepositCommand withdraw(int amount);
}
