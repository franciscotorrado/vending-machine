package com.machines.vending.domain.commands.deposit;

import com.machines.vending.domain.models.Deposit;
import com.machines.vending.domain.models.DepositInfo;
import com.machines.vending.infrastructure.persistence.entities.DepositEntity;
import com.machines.vending.infrastructure.persistence.repositories.DepositRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static com.machines.vending.utils.TestAmounts.FIVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadDepositCommandCommandImplTest {

    private ReadDepositCommand readDepositCommand;

    @Mock
    private DepositRepository depositRepository;
    private int buyerId;
    private int id;
    private int amount;

    @BeforeEach
    void setUp() {
        readDepositCommand = new ReadDepositCommandImpl(depositRepository);
        buyerId = new Random().nextInt();
        id = new Random().nextInt();
        amount = FIVE;
    }

    @Test
    void shouldReset() {
        // given
        final Deposit deposit = Deposit.builder().buyerId(buyerId).build();
        final DepositEntity depositEntity = DepositEntity.builder().id(id).buyerId(buyerId).amount(FIVE).build();

        when(depositRepository.findByBuyerId(buyerId)).thenReturn(Optional.of(depositEntity));

        // when
        final DepositInfo storedDeposit = readDepositCommand.read(deposit);

        // then
        assertThat(storedDeposit.getAvailableAmount()).isEqualTo(amount);
    }
}
