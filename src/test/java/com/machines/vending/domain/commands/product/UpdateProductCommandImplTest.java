package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.exceptions.product.NotValidProductCostException;
import com.machines.vending.domain.exceptions.product.NotValidProductNameException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import com.machines.vending.domain.models.Product;
import com.machines.vending.infraestructure.persistence.entities.ProductEntity;
import com.machines.vending.infraestructure.persistence.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static com.machines.vending.utils.TestAmounts.FIVE;
import static com.machines.vending.utils.TestAmounts.THREE;
import static com.machines.vending.utils.TestAmounts.TWENTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateProductCommandImplTest {

    private UpdateProductCommand updateProductCommand;

    @Mock
    private ProductRepository productRepository;

    private String productName;
    private int id;

    @BeforeEach
    void setUp() {
        updateProductCommand = new UpdateProductCommandImpl(productRepository);
        id = new Random().nextInt();
        productName = "Fresh Water";
    }

    @Test
    void updateProduct() throws NotValidProductCostException, NotValidProductNameException, ProductNotFoundException {
        //given
        final Product productToUpdate = Product.builder().id(id).productName(productName).cost(TWENTY).build();
        when(productRepository.existsById(id)).thenReturn(true);

        //when
        updateProductCommand.execute(productToUpdate);

        //then
        final ArgumentCaptor<ProductEntity> productEntityCaptor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(productRepository).save(productEntityCaptor.capture());
        final ProductEntity productEntity = productEntityCaptor.getValue();
        assertThat(productToUpdate.getId()).isEqualTo(productEntity.getId());
        assertThat(productToUpdate.getProductName()).isEqualTo(productEntity.getProductName());
        assertThat(productToUpdate.getSellerId()).isEqualTo(productEntity.getSellerId());
        assertThat(productToUpdate.getCost()).isEqualTo(productEntity.getCost());
        assertThat(productToUpdate.getAmountAvailable()).isEqualTo(productEntity.getAmountAvailable());
    }

    @Test
    void shouldThrowsProductNotFoundException_whenProductNotExists() {
        // given
        final Product productToUpdate = Product.builder().id(id).productName(productName).cost(FIVE).build();
        when(productRepository.existsById(id)).thenReturn(false);

        // when
        // then
        assertThrows(ProductNotFoundException.class, () -> updateProductCommand.execute(productToUpdate));
    }

    @Test
    void shouldThrowsNotValidCostException_whenCostIsNotMultipleOfFive() {
        // given
        final Product productToUpdate = Product.builder().cost(THREE).build();

        // when
        // then
        assertThrows(NotValidProductCostException.class, () -> updateProductCommand.execute(productToUpdate));
    }

    @Test
    void shouldThrowsNotValidNameException_whenNameIsNullOrEmpty() {
        // given
        // when
        final Product productToUpdate = Product.builder().productName("").build();

        // then
        assertThrows(NotValidProductNameException.class, () -> updateProductCommand.execute(productToUpdate));
    }
}
