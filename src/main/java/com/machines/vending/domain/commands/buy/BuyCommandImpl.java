package com.machines.vending.domain.commands.buy;

import com.machines.vending.domain.commands.deposit.WithdrawFromDepositCommand;
import com.machines.vending.domain.commands.product.ReduceProductAmountAvailableCommand;
import com.machines.vending.domain.exceptions.deposit.NotEnoughDepositException;
import com.machines.vending.domain.exceptions.product.NotEnoughProductAmountAvailableException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import com.machines.vending.domain.models.Deposit;
import com.machines.vending.domain.models.Purchase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class BuyCommandImpl implements BuyCommand {
    private final WithdrawFromDepositCommand withdrawFromDepositCommand;
    private final ReduceProductAmountAvailableCommand reduceProductAmountAvailableCommand;

    @Override
    @Transactional
    public void execute(int buyerId,
                        Purchase purchase) throws NotEnoughDepositException, NotEnoughProductAmountAvailableException, ProductNotFoundException {
        withdrawFromDepositCommand.withdraw(purchase.getAmount()).from(Deposit.builder().buyerId(buyerId).build());
        reduceProductAmountAvailableCommand.execute(purchase.getProductId(), purchase.getAmount());
    }
}
