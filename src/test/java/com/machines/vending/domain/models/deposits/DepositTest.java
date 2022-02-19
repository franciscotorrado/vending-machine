package com.machines.vending.domain.models.deposits;

import com.machines.vending.application.exceptions.NotEnoughDepositException;
import com.machines.vending.domain.models.Coin;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DepositTest {

    private final int five = 5;
    private final int ten = 10;
    private final int twenty = 20;
    private final int id = new Random().nextInt();
    private final int buyerId = new Random().nextInt();

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
        final Deposit deposit = new Deposit(id, buyerId, ten);

        //when
        deposit.add(five);

        //then
        assertThat(deposit.getAmount()).isEqualTo(15);
    }

    @Test
    void shouldRemoveAmount() throws NotEnoughDepositException {
        //given
        final Deposit deposit = new Deposit(id, buyerId, ten);

        //when
        deposit.remove(five);

        //then
        assertThat(deposit.getAmount()).isEqualTo(5);
    }

    @Test
    void shouldReset() {
        //given
        final Deposit deposit = new Deposit(id, buyerId, five);

        //when
        deposit.reset();

        //then
        assertThat(deposit.getAmount()).isEqualTo(0);
    }

    @Test
    void shouldThrowsNotEnoughDepositException_whenAmountToWithdrawIsOverAvailableDeposit() {
        // given
        final Deposit deposit = new Deposit(null, 0, ten);

        // when
        // then
        assertThrows(NotEnoughDepositException.class, () -> deposit.remove(twenty));
    }
}
