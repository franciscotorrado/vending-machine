package com.machines.vending.domain.commands.buy;

import com.machines.vending.domain.exceptions.deposit.NotEnoughDepositException;
import com.machines.vending.domain.exceptions.product.NotEnoughProductAmountAvailableException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import com.machines.vending.domain.models.Invoice;
import com.machines.vending.domain.models.Purchase;

import javax.transaction.Transactional;

public interface BuyCommand {
    @Transactional
    Invoice execute(int buyerId, Purchase purchase) throws NotEnoughDepositException, NotEnoughProductAmountAvailableException, ProductNotFoundException;
}
