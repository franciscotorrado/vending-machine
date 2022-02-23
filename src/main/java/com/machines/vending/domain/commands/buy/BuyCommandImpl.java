package com.machines.vending.domain.commands.buy;

import com.machines.vending.domain.commands.deposit.WithdrawFromDepositCommand;
import com.machines.vending.domain.commands.product.ReadProductCommand;
import com.machines.vending.domain.commands.product.ReduceProductAmountAvailableCommand;
import com.machines.vending.domain.exceptions.deposit.NotEnoughDepositException;
import com.machines.vending.domain.exceptions.product.NotEnoughProductAmountAvailableException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import com.machines.vending.domain.models.Deposit;
import com.machines.vending.domain.models.Invoice;
import com.machines.vending.domain.models.Product;
import com.machines.vending.domain.models.Purchase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class BuyCommandImpl implements BuyCommand {
    private final WithdrawFromDepositCommand withdrawFromDepositCommand;
    private final ReduceProductAmountAvailableCommand reduceProductAmountAvailableCommand;
    private final ReadProductCommand readProductCommand;

    @Override
    @Transactional
    public Invoice execute(int buyerId,
                           Purchase purchase) throws NotEnoughDepositException, NotEnoughProductAmountAvailableException, ProductNotFoundException {
        final int amount = purchase.getAmount();
        final Product product = readProductCommand.execute(Product.builder().id(purchase.getProductId()).build());
        final int total = amount * product.getCost();

        withdrawFromDepositCommand.withdraw(total).from(Deposit.builder().buyerId(buyerId).build());
        reduceProductAmountAvailableCommand.execute(purchase.getProductId(), purchase.getAmount());

        return Invoice.builder().productName(product.getProductName())
                .cost(product.getCost())
                .amount(amount)
                .total(total)
                .build();
    }
}
