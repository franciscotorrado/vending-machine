package com.machines.vending.domain.commands.buy;

import com.machines.vending.domain.models.Purchase;
import com.machines.vending.domain.exceptions.deposit.NotEnoughDepositException;
import com.machines.vending.domain.exceptions.product.NotEnoughProductAmountAvailableException;
import com.machines.vending.domain.exceptions.product.NotValidProductCostException;
import com.machines.vending.domain.exceptions.product.NotValidProductNameException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;

import javax.transaction.Transactional;

public interface BuyCommand {
    @Transactional
    void execute(Purchase purchase) throws NotEnoughDepositException, ProductNotFoundException, NotValidProductCostException, NotValidProductNameException, NotEnoughProductAmountAvailableException;
}
