package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.exceptions.product.NotEnoughProductAmountAvailableException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import com.machines.vending.infraestructure.persistence.entities.ProductEntity;
import com.machines.vending.infraestructure.persistence.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static com.machines.vending.utils.TestAmounts.SEVENTEEN;
import static com.machines.vending.utils.TestAmounts.TEN;
import static com.machines.vending.utils.TestAmounts.THREE;
import static com.machines.vending.utils.TestAmounts.TWENTY;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReduceProductAmountAvailableCommandImplTest {

    private ReduceProductAmountAvailableCommand reduceProductAmountAvailableCommand;

    @Mock
    private ProductRepository productRepository;

    private int productId;

    @BeforeEach
    void setUp() {
        reduceProductAmountAvailableCommand = new ReduceProductAmountAvailableCommandImpl(productRepository);
        productId = new Random().nextInt();
    }

    @Test
    void reduceAmountAvailable() throws NotEnoughProductAmountAvailableException, ProductNotFoundException {
        //given
        final ProductEntity productToUpdate = ProductEntity.builder().id(productId).amountAvailable(TWENTY).build();
        when(productRepository.findById(productId)).thenReturn(Optional.of(productToUpdate));

        //when
        reduceProductAmountAvailableCommand.execute(productId, THREE);

        //then
        verify(productRepository).updateAmountAvailable(productId, SEVENTEEN);
    }

    @Test
    void shouldThrowsNotEnoughProductAmountAvailableException_whenStockIsNotEnough() {
        //given
        final ProductEntity productToUpdate = ProductEntity.builder().id(productId).amountAvailable(TEN).build();
        when(productRepository.findById(productId)).thenReturn(Optional.of(productToUpdate));

        //when
        //then
        assertThrows(NotEnoughProductAmountAvailableException.class, () -> reduceProductAmountAvailableCommand.execute(productId, TWENTY));
    }

    @Test
    void shouldThrowsProductNotFoundException_whenProductNotExists() {
        //given
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        //when
        //then
        assertThrows(ProductNotFoundException.class, () -> reduceProductAmountAvailableCommand.execute(productId, TWENTY));
    }
}
