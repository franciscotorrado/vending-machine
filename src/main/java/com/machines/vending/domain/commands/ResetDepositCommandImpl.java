package com.machines.vending.domain.commands;

import com.machines.vending.domain.models.deposits.Deposit;
import com.machines.vending.infraestructure.persistence.deposits.DepositMapper;
import com.machines.vending.infraestructure.persistence.deposits.DepositRepository;
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
