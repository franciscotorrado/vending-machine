package com.machines.vending.domain.commands.deposit;

import com.machines.vending.domain.models.Deposit;
import com.machines.vending.domain.models.DepositInfo;

public interface ReadDepositCommand {
    DepositInfo read(final Deposit deposit);
}
