package com.machines.vending.domain.commands;

import com.machines.vending.domain.exceptions.InvalidCoinException;
import com.machines.vending.domain.models.Coin;
import com.machines.vending.domain.models.Deposit;
import com.machines.vending.infraestructure.persistence.entities.DepositEntity;
import com.machines.vending.infraestructure.persistence.entities.DepositMapper;
import com.machines.vending.infraestructure.persistence.entities.DepositRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddDepositCommandImpl implements AddDepositCommand {

    private final DepositRepository depositRepository;

    @Override
    public ToDepositCommand add(final int coin) throws InvalidCoinException {
        Coin.validate(coin);

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
