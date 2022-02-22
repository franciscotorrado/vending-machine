package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.models.Product;
import com.machines.vending.infrastructure.persistence.mappers.ProductMapper;
import com.machines.vending.infrastructure.persistence.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReadAllProductCommandImpl implements ReadAllProductsCommand {
    private final ProductRepository productRepository;

    @Override
    public List<Product> execute() {
        return productRepository.findAll()
                .stream()
                .map(p -> ProductMapper.fromEntity(p).toModel())
                .collect(Collectors.toList());
    }
}
