package com.machines.vending.domain.commands.deposit;

import com.machines.vending.domain.models.Deposit;
import com.machines.vending.infrastructure.persistence.entities.DepositEntity;
import com.machines.vending.infrastructure.persistence.mappers.DepositMapper;
import com.machines.vending.infrastructure.persistence.repositories.DepositRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReadDepositCommandImpl implements ReadDepositCommand {
    private final DepositRepository depositRepository;

    @Override
    public Deposit read(final Deposit deposit) {
        return DepositMapper
                .fromEntity(depositRepository.findByBuyerId(deposit.getBuyerId())
                        .orElse(DepositEntity.builder().build()))
                .toModel();
    }
}
