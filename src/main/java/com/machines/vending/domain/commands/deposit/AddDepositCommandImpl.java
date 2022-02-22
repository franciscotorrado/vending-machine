package com.machines.vending.domain.commands.deposit;

import com.machines.vending.domain.exceptions.coin.InvalidCoinException;
import com.machines.vending.domain.models.Coin;
import com.machines.vending.domain.models.Deposit;
import com.machines.vending.infrastructure.persistence.entities.DepositEntity;
import com.machines.vending.infrastructure.persistence.mappers.DepositMapper;
import com.machines.vending.infrastructure.persistence.repositories.DepositRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
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
