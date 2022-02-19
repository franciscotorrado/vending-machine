package com.machines.vending.domain.commands;

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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RemoveFromDepositCommandImplTest {

    private RemoveFromDepositCommand removeFromDepositCommand;

    @Mock
    private DepositRepository depositRepository;

    @BeforeEach
    void setUp() {
        removeFromDepositCommand = new RemoveFromDepositCommandImpl(depositRepository);
    }

    @Test
    void shouldRemove() {
        // given
        final int five = Coin.FIVE.getValue();
        final int ten = Coin.TEN.getValue();
        final int id = new Random().nextInt();
        final int buyerId = new Random().nextInt();
        final Deposit deposit = new Deposit(id, buyerId, five);
        final DepositEntity storedDeposit = DepositEntity.builder().id(id).buyerId(buyerId).amount(ten).build();

        when(depositRepository.findByBuyerId(buyerId)).thenReturn(Optional.of(storedDeposit));

        // when
        removeFromDepositCommand.remove(five).from(deposit);

        // then
        final ArgumentCaptor<DepositEntity> depositEntityCapture = ArgumentCaptor.forClass(DepositEntity.class);
        verify(depositRepository).save(depositEntityCapture.capture());
        final DepositEntity updatedDepositEntity = depositEntityCapture.getValue();
        assertThat(updatedDepositEntity.getBuyerId()).isEqualTo(buyerId);
        assertThat(updatedDepositEntity.getAmount()).isEqualTo(five);
    }

}
