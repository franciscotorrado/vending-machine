package com.machines.vending.domain.models.deposits;

import com.machines.vending.domain.exceptions.NotEnoughDepositException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.machines.vending.utils.TestAmounts.FIFTEEN;
import static com.machines.vending.utils.TestAmounts.FIVE;
import static com.machines.vending.utils.TestAmounts.TEN;
import static com.machines.vending.utils.TestAmounts.TWENTY;
import static com.machines.vending.utils.TestAmounts.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DepositTest {

    private int id;
    private int buyerId;

    @BeforeEach
    void setUp() {
        id = new Random().nextInt();
        buyerId = new Random().nextInt();
    }

    @Test
    void shouldCreateEmptyDeposit() {
        // given
        // when
        final Deposit deposit = new Deposit(buyerId);

        // then
        assertThat(deposit.getBuyerId()).isEqualTo(buyerId);
        assertThat(deposit.getAmount()).isZero();
    }

    @Test
    void shouldAddCoin() {
        //given
        final Deposit deposit = new Deposit(id, buyerId, TEN);

        //when
        deposit.add(FIVE);

        //then
        assertThat(deposit.getAmount()).isEqualTo(FIFTEEN);
    }

    @Test
    void shouldWithdrawAmount() throws NotEnoughDepositException {
        //given
        final Deposit deposit = new Deposit(id, buyerId, TEN);

        //when
        deposit.withdraw(FIVE);

        //then
        assertThat(deposit.getAmount()).isEqualTo(FIVE);
    }

    @Test
    void shouldReset() {
        //given
        final Deposit deposit = new Deposit(id, buyerId, FIVE);

        //when
        deposit.reset();

        //then
        assertThat(deposit.getAmount()).isEqualTo(ZERO);
    }

    @Test
    void shouldThrowsNotEnoughDepositException_whenAmountToWithdrawIsOverAvailableDeposit() {
        // given
        final Deposit deposit = new Deposit(null, 0, TEN);

        // when
        // then
        assertThrows(NotEnoughDepositException.class, () -> deposit.withdraw(TWENTY));
    }
}
