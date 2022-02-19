package com.machines.vending.domain.commands;

public interface WithdrawFromDepositCommand {
    FromDepositCommand withdraw(int amount);
}
