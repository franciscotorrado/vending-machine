package com.machines.vending.domain.commands.deposit;

import com.machines.vending.domain.models.Deposit;
import com.machines.vending.infrastructure.persistence.entities.DepositEntity;
import com.machines.vending.infrastructure.persistence.repositories.DepositRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static com.machines.vending.utils.TestAmounts.FIVE;
import static com.machines.vending.utils.TestAmounts.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResetToDepositCommandImplTest {

    private ResetDepositCommand resetDepositCommand;

    @Mock
    private DepositRepository depositRepository;
    private int buyerId;
    private int id;

    @BeforeEach
    void setUp() {
        resetDepositCommand = new ResetDepositCommandImpl(depositRepository);
        buyerId = new Random().nextInt();
        id = new Random().nextInt();
    }

    @Test
    void shouldReset() {
        // given
        final Deposit deposit = Deposit.builder().id(id).buyerId(buyerId).amount(FIVE).build();
        final DepositEntity depositEntity = DepositEntity.builder().id(id).buyerId(buyerId).amount(FIVE).build();
        final DepositEntity updateDeposit = DepositEntity.builder().id(id).buyerId(buyerId).amount(ZERO).build();

        when(depositRepository.findByBuyerId(anyInt())).thenReturn(Optional.of(depositEntity));
        when(depositRepository.save(any())).thenReturn(updateDeposit);

        // when
        resetDepositCommand.reset(deposit);

        // then
        final ArgumentCaptor<DepositEntity> depositEntityCapture = ArgumentCaptor.forClass(DepositEntity.class);
        verify(depositRepository).findByBuyerId(buyerId);
        verify(depositRepository).save(depositEntityCapture.capture());
        final DepositEntity updatedDepositEntity = depositEntityCapture.getValue();
        assertThat(updatedDepositEntity.getBuyerId()).isEqualTo(buyerId);
        assertThat(updatedDepositEntity.getAmount()).isEqualTo(ZERO);
    }

    @Test
    void shouldNotResetEmptyDeposit() {
        // given
        final Deposit deposit = Deposit.builder().id(id).buyerId(buyerId).amount(FIVE).build();

        // when
        resetDepositCommand.reset(deposit);

        // then
        verify(depositRepository, times(0)).save(any());
    }

}
