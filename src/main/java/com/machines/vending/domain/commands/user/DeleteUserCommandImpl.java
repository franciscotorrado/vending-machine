package com.machines.vending.domain.commands.user;

import com.machines.vending.domain.commands.deposit.ReadDepositCommand;
import com.machines.vending.domain.exceptions.PositiveDepositAvailableException;
import com.machines.vending.domain.models.Deposit;
import com.machines.vending.domain.models.User;
import com.machines.vending.infrastructure.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class DeleteUserCommandImpl implements DeleteUserCommand {
    private final UserRepository userRepository;
    private final ReadDepositCommand readDepositCommand;

    @Override
    @Transactional
    public void execute(final User user) throws PositiveDepositAvailableException {
        int depositAmount = readDepositCommand.read(Deposit.builder().buyerId(user.getId()).build()).getAvailableAmount();
        if (depositAmount > 0) {
            throw new PositiveDepositAvailableException();
        }
        userRepository.deleteById(user.getId());
    }
}
