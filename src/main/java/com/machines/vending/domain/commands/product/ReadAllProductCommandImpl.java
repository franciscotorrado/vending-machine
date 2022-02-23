package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.models.ProductItem;
import com.machines.vending.domain.models.Role;
import com.machines.vending.infrastructure.persistence.mappers.ProductItemMapper;
import com.machines.vending.infrastructure.persistence.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.machines.vending.domain.models.Role.BUYER;

@Service
@AllArgsConstructor
public class ReadAllProductCommandImpl implements ReadAllProductsCommand {
    private final ProductRepository productRepository;

    @Override
    public List<ProductItem> execute(int userId, Role role) {
        if (BUYER.equals(role)) {
            return productRepository.findAll()
                    .stream()
                    .map(p -> ProductItemMapper.fromEntity(p).toModel())
                    .collect(Collectors.toList());
        } else {
            return productRepository.findBySellerId(userId)
                    .stream()
                    .map(p -> ProductItemMapper.fromEntity(p).toModel())
                    .collect(Collectors.toList());
        }
    }
}
