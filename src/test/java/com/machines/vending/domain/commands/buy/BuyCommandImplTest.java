package com.machines.vending.domain.commands.buy;

import com.machines.vending.domain.commands.deposit.FromDepositCommand;
import com.machines.vending.domain.commands.deposit.WithdrawFromDepositCommand;
import com.machines.vending.domain.commands.product.ReadProductCommand;
import com.machines.vending.domain.commands.product.ReduceProductAmountAvailableCommand;
import com.machines.vending.domain.exceptions.deposit.NotEnoughDepositException;
import com.machines.vending.domain.exceptions.product.NotEnoughProductAmountAvailableException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import com.machines.vending.domain.models.Deposit;
import com.machines.vending.domain.models.Product;
import com.machines.vending.domain.models.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Random;

import static com.machines.vending.utils.TestAmounts.THREE;
import static com.machines.vending.utils.TestAmounts.TWENTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BuyCommandImplTest {

    private final WithdrawFromDepositCommand withdrawFromDepositCommand = Mockito.mock(WithdrawFromDepositCommand.class);
    private final ReduceProductAmountAvailableCommand reduceProductAmountAvailableCommand = Mockito.mock(ReduceProductAmountAvailableCommand.class);
    private final ReadProductCommand readProductCommand = Mockito.mock(ReadProductCommand.class);
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
        final Product product = Product.builder().id(productId).cost(TWENTY).amountAvailable(10).build();
        when(readProductCommand.execute(any())).thenReturn(product);
        doAnswer(invocation -> null).when(fromDepositCommand).from(any());
        doAnswer(invocation -> fromDepositCommand).when(withdrawFromDepositCommand).withdraw(amountToBuy*product.getCost());

        doAnswer(invocation -> null).when(reduceProductAmountAvailableCommand).execute(productId, amountToBuy);

        buyCommand = new BuyCommandImpl(withdrawFromDepositCommand, reduceProductAmountAvailableCommand, readProductCommand);

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
    void shouldThrowNotEnoughDepositException_whenDepositIsNotEnough() throws NotEnoughDepositException, ProductNotFoundException {
        // given
        final Product product = Product.builder().id(productId).cost(TWENTY).amountAvailable(10).build();
        when(readProductCommand.execute(any())).thenReturn(product);
        doThrow(NotEnoughDepositException.class).when(fromDepositCommand).from(any());
        doAnswer(invocation -> fromDepositCommand).when(withdrawFromDepositCommand).withdraw(amountToBuy*product.getCost());

        buyCommand = new BuyCommandImpl(withdrawFromDepositCommand, reduceProductAmountAvailableCommand, readProductCommand);

        final Purchase purchase = Purchase.builder().productId(productId).amount(amountToBuy).build();

        // when
        // then
        assertThrows(NotEnoughDepositException.class, () -> buyCommand.execute(buyerId, purchase));
    }

    @Test
    void shouldThrowNotEnoughProductAmountAvailableException_whenStockIsNotEnough() throws ProductNotFoundException, NotEnoughDepositException, NotEnoughProductAmountAvailableException {
        // given
        final Product product = Product.builder().id(productId).cost(TWENTY).amountAvailable(0).build();
        when(readProductCommand.execute(any())).thenReturn(product);
        doAnswer(invocation -> null).when(fromDepositCommand).from(any());
        doAnswer(invocation -> fromDepositCommand).when(withdrawFromDepositCommand).withdraw(amountToBuy*product.getCost());

        doThrow(NotEnoughProductAmountAvailableException.class).when(reduceProductAmountAvailableCommand).execute(productId, amountToBuy);

        buyCommand = new BuyCommandImpl(withdrawFromDepositCommand, reduceProductAmountAvailableCommand, readProductCommand);

        final Purchase purchase = Purchase.builder().productId(productId).amount(amountToBuy).build();

        // when
        // then
        assertThrows(NotEnoughProductAmountAvailableException.class, () -> buyCommand.execute(buyerId, purchase));
    }
}
