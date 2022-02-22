package com.machines.vending.domain.commands.deposit;

import com.machines.vending.domain.exceptions.deposit.NotEnoughDepositException;
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
import static com.machines.vending.utils.TestAmounts.TEN;
import static com.machines.vending.utils.TestAmounts.THIRTY;
import static com.machines.vending.utils.TestAmounts.TWENTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WithdrawFromDepositCommandImplTest {

    private int id;
    private int buyerId;

    private WithdrawFromDepositCommand withdrawFromDepositCommand;

    @Mock
    private DepositRepository depositRepository;

    @BeforeEach
    void setUp() {
        withdrawFromDepositCommand = new WithdrawFromDepositCommandImpl(depositRepository);
        id = new Random().nextInt();
        buyerId = new Random().nextInt();
    }

    @Test
    void shouldWithdraw() throws NotEnoughDepositException {
        // given
        final Deposit deposit = Deposit.builder().buyerId(buyerId).build();
        final DepositEntity storedDeposit = DepositEntity.builder().id(id).buyerId(buyerId).amount(TEN).build();

        when(depositRepository.findByBuyerId(buyerId)).thenReturn(Optional.of(storedDeposit));

        // when
        withdrawFromDepositCommand.withdraw(FIVE).from(deposit);

        // then
        final ArgumentCaptor<DepositEntity> depositEntityCapture = ArgumentCaptor.forClass(DepositEntity.class);
        verify(depositRepository).save(depositEntityCapture.capture());
        final DepositEntity updatedDepositEntity = depositEntityCapture.getValue();
        assertThat(updatedDepositEntity.getBuyerId()).isEqualTo(buyerId);
        assertThat(updatedDepositEntity.getAmount()).isEqualTo(FIVE);
    }

    @Test
    void shouldThrowsNotEnoughDepositException_whenAmountToWithdrawIsNotAvailable() {
        // given
        final Deposit deposit = Deposit.builder().buyerId(buyerId).build();
        final DepositEntity storedDeposit = DepositEntity.builder().id(id).buyerId(buyerId).amount(TWENTY).build();

        when(depositRepository.findByBuyerId(buyerId)).thenReturn(Optional.of(storedDeposit));

        // when
        // then
        assertThrows(NotEnoughDepositException.class, () -> withdrawFromDepositCommand.withdraw(THIRTY).from(deposit));
    }

}
