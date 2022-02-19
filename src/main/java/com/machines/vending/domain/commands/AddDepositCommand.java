package com.machines.vending.domain.commands;

import com.machines.vending.domain.exceptions.InvalidCoinException;

public interface AddDepositCommand {
    ToDepositCommand add(int coin) throws InvalidCoinException;
}
