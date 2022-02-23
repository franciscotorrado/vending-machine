package com.machines.vending.domain.commands.deposit;

import com.machines.vending.domain.models.Deposit;
import com.machines.vending.domain.models.DepositInfo;
import com.machines.vending.infrastructure.persistence.entities.DepositEntity;
import com.machines.vending.infrastructure.persistence.repositories.DepositRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReadDepositCommandImpl implements ReadDepositCommand {
    private final DepositRepository depositRepository;

    @Override
    public DepositInfo read(final Deposit deposit) {
        return DepositInfo.builder()
                .amount(depositRepository.findByBuyerId(deposit.getBuyerId())
                        .orElse(DepositEntity.builder().build()).getAmount())
                .build();
    }
}
