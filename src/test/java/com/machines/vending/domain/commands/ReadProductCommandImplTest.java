package com.machines.vending.domain.commands;

import com.machines.vending.domain.exceptions.ProductNotFoundException;
import com.machines.vending.domain.models.Product;
import com.machines.vending.infraestructure.persistence.entities.ProductEntity;
import com.machines.vending.infraestructure.persistence.entities.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static com.machines.vending.utils.TestAmounts.TWENTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadProductCommandImplTest {

    private ReadProductCommand readProductCommand;

    @Mock
    private ProductRepository productRepository;

    private int id;
    private String productName;

    @BeforeEach
    void setUp() {
        readProductCommand = new ReadProductCommandImpl(productRepository);
        id = new Random().nextInt();
        productName = "Fresh Water";
    }

    @Test
    void readProduct() throws ProductNotFoundException {
        //given
        final Product productToRead = Product.builder().id(id).build();
        when(productRepository.findById(id)).thenReturn(Optional.of(ProductEntity.builder().id(id).productName(productName)
                .cost(TWENTY).sellerId(10).amountAvailable(12).build()));

        //when
        final Product storedProduct = readProductCommand.execute(productToRead);

        //then
        assertThat(storedProduct.getId()).isEqualTo(id);
        assertThat(storedProduct.getProductName()).isEqualTo(productName);
        assertThat(storedProduct.getSellerId()).isEqualTo(10);
        assertThat(storedProduct.getAmountAvailable()).isEqualTo(12);
        assertThat(storedProduct.getCost()).isEqualTo(TWENTY);
    }

    @Test
    void shouldThrowsProductNotFoundException_whenProductNotExists() {
        // given
        final Product productToUpdate = Product.builder().id(id).build();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // when
        // then
        assertThrows(ProductNotFoundException.class, () -> readProductCommand.execute(productToUpdate));
    }
}
