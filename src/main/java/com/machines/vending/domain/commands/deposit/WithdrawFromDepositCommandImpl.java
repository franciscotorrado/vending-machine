package com.machines.vending.domain.commands.deposit;

import com.machines.vending.domain.models.Deposit;
import com.machines.vending.infrastructure.persistence.entities.DepositEntity;
import com.machines.vending.infrastructure.persistence.mappers.DepositMapper;
import com.machines.vending.infrastructure.persistence.repositories.DepositRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WithdrawFromDepositCommandImpl implements WithdrawFromDepositCommand {

    private final DepositRepository depositRepository;

    @Override
    public FromDepositCommand withdraw(final int amount) {
        return depositToBeUpdated -> {
            final int buyerId = depositToBeUpdated.getBuyerId();
            final DepositEntity depositEntity = depositRepository.findByBuyerId(buyerId)
                    .orElse(DepositEntity.builder().buyerId(buyerId).build());
            final Deposit deposit = DepositMapper.fromEntity(depositEntity).toModel();
            deposit.withdraw(amount);
            depositRepository.save(DepositMapper.fromModel(deposit).toEntity());
        };
    }
}
