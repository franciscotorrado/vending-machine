package com.machines.vending.domain.commands;

import com.machines.vending.domain.exceptions.InvalidCoinException;
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

import static com.machines.vending.utils.TestAmounts.FIVE;
import static com.machines.vending.utils.TestAmounts.TEN;
import static com.machines.vending.utils.TestAmounts.THREE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddToDepositCommandImplTest {

    private int id;
    private int buyerId;

    private AddDepositCommand addDepositCommand;

    @Mock
    private DepositRepository depositRepository;

    @BeforeEach
    void setUp() {
        addDepositCommand = new AddDepositCommandImpl(depositRepository);
        id = new Random().nextInt();
        buyerId = new Random().nextInt();
    }

    @Test
    void shouldAdd() throws InvalidCoinException {
        // given
        final Deposit deposit = new Deposit(id, buyerId, FIVE);
        final DepositEntity storedDeposit = DepositEntity.builder().id(id).buyerId(buyerId).amount(FIVE).build();

        when(depositRepository.findByBuyerId(buyerId)).thenReturn(Optional.of(storedDeposit));

        // when
        addDepositCommand.add(FIVE).to(deposit);

        // then
        final ArgumentCaptor<DepositEntity> depositEntityCapture = ArgumentCaptor.forClass(DepositEntity.class);
        verify(depositRepository).save(depositEntityCapture.capture());
        final DepositEntity updatedDepositEntity = depositEntityCapture.getValue();
        assertThat(updatedDepositEntity.getBuyerId()).isEqualTo(buyerId);
        assertThat(updatedDepositEntity.getAmount()).isEqualTo(TEN);
    }

    @Test
    void shouldThrowsInvalidCoinException_whenAnInvalidCoinIsReceived() {
        // given
        // when
        // then
        assertThrows(InvalidCoinException.class, () -> addDepositCommand.add(THREE));
    }

}
