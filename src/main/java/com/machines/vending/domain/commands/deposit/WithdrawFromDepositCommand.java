package com.machines.vending.domain.commands.deposit;

public interface WithdrawFromDepositCommand {
    FromDepositCommand withdraw(int amount);
}
