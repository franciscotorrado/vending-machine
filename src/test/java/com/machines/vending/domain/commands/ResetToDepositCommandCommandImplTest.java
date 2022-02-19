package com.machines.vending.domain.commands;

import com.machines.vending.domain.models.Deposit;
import com.machines.vending.infraestructure.persistence.entities.DepositEntity;
import com.machines.vending.infraestructure.persistence.entities.DepositRepository;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResetToDepositCommandCommandImplTest {

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

        when(depositRepository.findById(any())).thenReturn(Optional.of(depositEntity));

        // when
        resetDepositCommand.reset(deposit);

        // then
        final ArgumentCaptor<DepositEntity> depositEntityCapture = ArgumentCaptor.forClass(DepositEntity.class);
        verify(depositRepository).findById(buyerId);
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
