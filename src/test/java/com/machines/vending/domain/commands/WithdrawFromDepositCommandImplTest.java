package com.machines.vending.domain.commands;

import com.machines.vending.application.exceptions.NotEnoughDepositException;
import com.machines.vending.domain.models.Coin;
import com.machines.vending.domain.models.deposits.Deposit;
import com.machines.vending.infraestructure.persistence.deposits.DepositEntity;
import com.machines.vending.infraestructure.persistence.deposits.DepositRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WithdrawFromDepositCommandImplTest {

    private WithdrawFromDepositCommand withdrawFromDepositCommand;

    @Mock
    private DepositRepository depositRepository;

    @BeforeEach
    void setUp() {
        withdrawFromDepositCommand = new WithdrawFromDepositCommandImpl(depositRepository);
    }

    @Test
    void shouldWithdraw() throws NotEnoughDepositException {
        // given
        final int five = Coin.FIVE.getValue();
        final int ten = Coin.TEN.getValue();
        final int id = new Random().nextInt();
        final int buyerId = new Random().nextInt();
        final Deposit deposit = new Deposit(id, buyerId, five);
        final DepositEntity storedDeposit = DepositEntity.builder().id(id).buyerId(buyerId).amount(ten).build();

        when(depositRepository.findByBuyerId(buyerId)).thenReturn(Optional.of(storedDeposit));

        // when
        withdrawFromDepositCommand.withdraw(five).from(deposit);

        // then
        final ArgumentCaptor<DepositEntity> depositEntityCapture = ArgumentCaptor.forClass(DepositEntity.class);
        verify(depositRepository).save(depositEntityCapture.capture());
        final DepositEntity updatedDepositEntity = depositEntityCapture.getValue();
        assertThat(updatedDepositEntity.getBuyerId()).isEqualTo(buyerId);
        assertThat(updatedDepositEntity.getAmount()).isEqualTo(five);
    }

    @Test
    void shouldThrowNotEnoughDepositException_whenAmountToWithdrawIsNotAvailable() {
        // given
        final int thirty = 30;
        final int twenty = 20;
        final int five = 5;
        final int id = new Random().nextInt();
        final int buyerId = new Random().nextInt();
        final Deposit deposit = new Deposit(id, buyerId, five);
        final DepositEntity storedDeposit = DepositEntity.builder().id(id).buyerId(buyerId).amount(twenty).build();

        when(depositRepository.findByBuyerId(buyerId)).thenReturn(Optional.of(storedDeposit));

        // when
        // then
        assertThrows(NotEnoughDepositException.class, () -> withdrawFromDepositCommand.withdraw(thirty).from(deposit));
    }

}
