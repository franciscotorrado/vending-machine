package com.machines.vending.domain.commands.deposit;

import com.machines.vending.domain.models.Deposit;
import com.machines.vending.infraestructure.persistence.mappers.DepositMapper;
import com.machines.vending.infraestructure.persistence.repositories.DepositRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResetDepositCommandImpl implements ResetDepositCommand {
    private final DepositRepository depositRepository;

    @Override
    public void reset(final Deposit deposit) {
        depositRepository.findById(deposit.getBuyerId()).ifPresent(d -> {
            deposit.reset();
            depositRepository.save(DepositMapper.fromModel(deposit).toEntity());
        });
    }
}
