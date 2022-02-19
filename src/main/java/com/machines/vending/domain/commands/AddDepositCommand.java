package com.machines.vending.domain.commands;

public interface AddDepositCommand {
    ToDepositCommand add(int coin);
}
