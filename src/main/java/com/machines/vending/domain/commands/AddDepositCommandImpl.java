package com.machines.vending.domain.commands;

import com.machines.vending.domain.models.deposits.Deposit;
import com.machines.vending.infraestructure.persistence.deposits.DepositEntity;
import com.machines.vending.infraestructure.persistence.deposits.DepositMapper;
import com.machines.vending.infraestructure.persistence.deposits.DepositRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddDepositCommandImpl implements AddDepositCommand {

    private final DepositRepository depositRepository;

    @Override
    public ToDepositCommand add(final int coin) {
        return depositToBeUpdated -> {
            final int buyerId = depositToBeUpdated.getBuyerId();
            final DepositEntity depositEntity = depositRepository.findByBuyerId(buyerId)
                    .orElse(DepositEntity.builder().buyerId(buyerId).build());
            final Deposit deposit = DepositMapper.fromEntity(depositEntity).toModel();
            deposit.add(coin);
            depositRepository.save(DepositMapper.fromModel(deposit).toEntity());
        };
    }
}
