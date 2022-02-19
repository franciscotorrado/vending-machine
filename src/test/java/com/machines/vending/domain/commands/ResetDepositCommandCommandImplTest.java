package com.machines.vending.domain.commands;

import com.machines.vending.domain.models.deposits.Coin;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResetDepositCommandCommandImplTest {
    private final static int ZERO = 0;

    private ResetDepositCommand resetDepositCommand;

    @Mock
    private DepositRepository depositRepository;

    @BeforeEach
    void setUp() {
        resetDepositCommand = new ResetDepositCommandImpl(depositRepository);
    }

    @Test
    void shouldReset() {
        // given
        final int five = Coin.FIVE.getValue();
        final Integer buyerId = new Random().nextInt();
        final Deposit deposit = new Deposit(buyerId, five);
        final DepositEntity depositEntity = new DepositEntity(buyerId, five);
        when(depositRepository.findById(any())).thenReturn(Optional.of(depositEntity));

        // when
        resetDepositCommand.reset(deposit);

        // then
        final ArgumentCaptor<DepositEntity> depositEntityCapture = ArgumentCaptor.forClass(DepositEntity.class);
        verify(depositRepository).findById(buyerId);
        verify(depositRepository).save(depositEntityCapture.capture());
        final DepositEntity updatedDepositEntity = depositEntityCapture.getValue();
        assertThat(updatedDepositEntity.getBuyerId()).isEqualTo(buyerId);
        assertThat(updatedDepositEntity.getValue()).isEqualTo(ZERO);
    }

    @Test
    void shouldNotResetEmptyDeposit() {
        // given
        final int five = Coin.FIVE.getValue();
        final Integer buyerId = new Random().nextInt();
        final Deposit deposit = new Deposit(buyerId, five);

        // when
        resetDepositCommand.reset(deposit);

        // then
        verify(depositRepository, times(0)).save(any());
    }

}
