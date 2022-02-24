package com.machines.vending.domain.commands.deposit;

import com.machines.vending.domain.models.Deposit;
import com.machines.vending.domain.models.DepositInfo;
import com.machines.vending.infrastructure.persistence.entities.DepositEntity;
import com.machines.vending.infrastructure.persistence.mappers.DepositMapper;
import com.machines.vending.infrastructure.persistence.repositories.DepositRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ResetDepositCommandImpl implements ResetDepositCommand {
    private final DepositRepository depositRepository;

    @Override
    @Transactional
    public DepositInfo reset(final Deposit deposit) {

        int amount = depositRepository.findByBuyerId(deposit.getBuyerId())
                .map(d -> {
                    Deposit updatedDeposit = DepositMapper.fromEntity(d).toModel();
                    updatedDeposit.reset();
                    return depositRepository.save(DepositMapper.fromModel(updatedDeposit).toEntity());
                })
                .orElse(DepositEntity.builder().build()).getAmount();

        return DepositInfo.builder().availableAmount(amount).build();
    }
}
