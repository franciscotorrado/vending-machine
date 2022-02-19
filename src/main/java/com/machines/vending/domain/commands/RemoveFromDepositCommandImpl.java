package com.machines.vending.domain.commands;

import com.machines.vending.domain.models.deposits.Deposit;
import com.machines.vending.infraestructure.persistence.deposits.DepositEntity;
import com.machines.vending.infraestructure.persistence.deposits.DepositMapper;
import com.machines.vending.infraestructure.persistence.deposits.DepositRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveFromDepositCommandImpl implements RemoveFromDepositCommand {

    public static final int ZERO = 0;
    private final DepositRepository depositRepository;

    @Override
    public FromDepositCommand remove(final int amount) {
        return depositToBeUpdated -> {
            final Integer buyerId = depositToBeUpdated.getBuyerId();
            final DepositEntity depositEntity = depositRepository.findById(buyerId).orElse(new DepositEntity(buyerId, ZERO));
            final Deposit deposit = DepositMapper.fromEntity(depositEntity).toModel();
            deposit.remove(amount);
            depositRepository.save(DepositMapper.fromModel(deposit).toEntity());
        };
    }
}
