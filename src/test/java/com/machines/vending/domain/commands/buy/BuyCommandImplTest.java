package com.machines.vending.domain.commands.buy;

import com.machines.vending.domain.commands.deposit.FromDepositCommand;
import com.machines.vending.domain.commands.deposit.WithdrawFromDepositCommand;
import com.machines.vending.domain.commands.product.ReduceProductAmountAvailableCommand;
import com.machines.vending.domain.exceptions.deposit.NotEnoughDepositException;
import com.machines.vending.domain.exceptions.product.NotEnoughProductAmountAvailableException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import com.machines.vending.domain.models.Deposit;
import com.machines.vending.domain.models.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Random;

import static com.machines.vending.utils.TestAmounts.THREE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

class BuyCommandImplTest {

    private final WithdrawFromDepositCommand withdrawFromDepositCommand = Mockito.mock(WithdrawFromDepositCommand.class);
    private final ReduceProductAmountAvailableCommand reduceProductAmountAvailableCommand = Mockito.mock(ReduceProductAmountAvailableCommand.class);
    private final FromDepositCommand fromDepositCommand = Mockito.mock(FromDepositCommand.class);
    private BuyCommand buyCommand;
    private int productId;
    private int buyerId;
    private int amountToBuy;

    @BeforeEach
    void setUp() {
        productId = new Random().nextInt();
        buyerId = new Random().nextInt();
        amountToBuy = THREE;
    }

    @Test
    void shouldBuyProduct() throws NotEnoughDepositException, ProductNotFoundException, NotEnoughProductAmountAvailableException {
        // given
        doAnswer(invocation -> null).when(fromDepositCommand).from(any());
        doAnswer(invocation -> fromDepositCommand).when(withdrawFromDepositCommand).withdraw(amountToBuy);

        doAnswer(invocation -> null).when(reduceProductAmountAvailableCommand).execute(productId, amountToBuy);

        buyCommand = new BuyCommandImpl(withdrawFromDepositCommand, reduceProductAmountAvailableCommand);

        // when
        buyCommand.execute(buyerId, Purchase.builder().productId(productId).amount(amountToBuy).build());

        // then
        final ArgumentCaptor<Deposit> depositQueryCaptor = ArgumentCaptor.forClass(Deposit.class);
        verify(fromDepositCommand).from(depositQueryCaptor.capture());
        final Deposit depositQuery = depositQueryCaptor.getValue();
        assertThat(depositQuery.getBuyerId()).isEqualTo(buyerId);

        verify(reduceProductAmountAvailableCommand).execute(productId, amountToBuy);
    }

    @Test
    void shouldThrowNotEnoughDepositException_whenDepositIsNotEnough() throws NotEnoughDepositException {
        // given
        doThrow(NotEnoughDepositException.class).when(fromDepositCommand).from(any());
        doAnswer(invocation -> fromDepositCommand).when(withdrawFromDepositCommand).withdraw(amountToBuy);

        buyCommand = new BuyCommandImpl(withdrawFromDepositCommand, reduceProductAmountAvailableCommand);

        final Purchase purchase = Purchase.builder().productId(productId).amount(amountToBuy).build();

        // when
        // then
        assertThrows(NotEnoughDepositException.class, () -> buyCommand.execute(buyerId, purchase));
    }

    @Test
    void shouldThrowNotEnoughProductAmountAvailableException_whenStockIsNotEnough() throws ProductNotFoundException, NotEnoughDepositException, NotEnoughProductAmountAvailableException {
        // given
        doAnswer(invocation -> null).when(fromDepositCommand).from(any());
        doAnswer(invocation -> fromDepositCommand).when(withdrawFromDepositCommand).withdraw(amountToBuy);

        doThrow(NotEnoughProductAmountAvailableException.class).when(reduceProductAmountAvailableCommand).execute(productId, amountToBuy);

        buyCommand = new BuyCommandImpl(withdrawFromDepositCommand, reduceProductAmountAvailableCommand);

        final Purchase purchase = Purchase.builder().productId(productId).amount(amountToBuy).build();

        // when
        // then
        assertThrows(NotEnoughProductAmountAvailableException.class, () -> buyCommand.execute(buyerId, purchase));
    }
}
