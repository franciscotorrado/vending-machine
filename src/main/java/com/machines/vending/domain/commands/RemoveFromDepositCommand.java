package com.machines.vending.domain.commands;

public interface RemoveFromDepositCommand {
    FromDepositCommand remove(int amount);
}
