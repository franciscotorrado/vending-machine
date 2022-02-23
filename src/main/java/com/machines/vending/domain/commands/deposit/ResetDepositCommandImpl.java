package com.machines.vending.domain.commands.deposit;

import com.machines.vending.domain.models.Deposit;
import com.machines.vending.domain.models.DepositInfo;
import com.machines.vending.infrastructure.persistence.entities.DepositEntity;
import com.machines.vending.infrastructure.persistence.mappers.DepositMapper;
import com.machines.vending.infrastructure.persistence.repositories.DepositRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResetDepositCommandImpl implements ResetDepositCommand {
    private final DepositRepository depositRepository;

    @Override
    public DepositInfo reset(final Deposit deposit) {

        int amount = depositRepository.findById(deposit.getBuyerId()).map(d -> {
            deposit.reset();
            return depositRepository.save(DepositMapper.fromModel(deposit).toEntity());
        }).orElse(DepositEntity.builder().build()).getAmount();

        return DepositInfo.builder().availableAmount(amount).build();
    }
}
