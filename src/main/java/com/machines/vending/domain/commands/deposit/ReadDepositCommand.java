package com.machines.vending.domain.commands.deposit;

import com.machines.vending.domain.models.Deposit;

public interface ReadDepositCommand {
    Deposit read(final Deposit deposit);
}
