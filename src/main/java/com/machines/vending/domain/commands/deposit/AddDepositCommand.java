package com.machines.vending.domain.commands.deposit;

import com.machines.vending.domain.exceptions.coin.InvalidCoinException;

public interface AddDepositCommand {
    ToDepositCommand add(int coin) throws InvalidCoinException;
}
