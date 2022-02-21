package com.machines.vending.domain.commands.buy;

import com.machines.vending.domain.commands.deposit.WithdrawFromDepositCommand;
import com.machines.vending.domain.commands.product.ReduceProductAmountAvailableCommand;
import com.machines.vending.domain.models.Purchase;
import com.machines.vending.domain.exceptions.deposit.NotEnoughDepositException;
import com.machines.vending.domain.exceptions.product.NotEnoughProductAmountAvailableException;
import com.machines.vending.domain.exceptions.product.NotValidProductCostException;
import com.machines.vending.domain.exceptions.product.NotValidProductNameException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import com.machines.vending.domain.models.Deposit;
import lombok.AllArgsConstructor;

import javax.transaction.Transactional;

@AllArgsConstructor
public class BuyCommandImpl implements BuyCommand {
    private final WithdrawFromDepositCommand withdrawFromDepositCommand;
    private final ReduceProductAmountAvailableCommand reduceProductAmountAvailableCommand;

    @Override
    @Transactional
    public void execute(Purchase purchase) throws NotEnoughDepositException, ProductNotFoundException, NotValidProductCostException, NotValidProductNameException, NotEnoughProductAmountAvailableException {
        withdrawFromDepositCommand.withdraw(purchase.getAmount()).from(Deposit.builder().buyerId(purchase.getBuyerId()).build());
        reduceProductAmountAvailableCommand.execute(purchase.getProductId(), purchase.getAmount());
    }
}
