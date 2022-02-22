package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.models.Product;
import com.machines.vending.infrastructure.persistence.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteProductCommandImplTest {

    private DeleteProductCommand deleteProductCommand;

    @Mock
    private ProductRepository productRepository;

    private int id;
    private int sellerId;

    @BeforeEach
    void setUp() {
        deleteProductCommand = new DeleteProductCommandImpl(productRepository);
        id = new Random().nextInt();
        sellerId = new Random().nextInt();
    }

    @Test
    void deleteProduct() {
        //given
        final Product productToDelete = Product.builder().id(id).sellerId(sellerId).build();

        //when
        deleteProductCommand.execute(productToDelete);

        //then
        verify(productRepository).deleteByIdAndSellerId(id, sellerId);
    }
}
