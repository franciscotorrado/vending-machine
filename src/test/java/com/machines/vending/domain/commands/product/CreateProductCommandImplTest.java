package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.exceptions.product.CreateProductWithGivenIdException;
import com.machines.vending.domain.exceptions.product.NotValidProductCostException;
import com.machines.vending.domain.exceptions.product.NotValidProductNameException;
import com.machines.vending.domain.models.Product;
import com.machines.vending.infrastructure.persistence.entities.ProductEntity;
import com.machines.vending.infrastructure.persistence.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static com.machines.vending.utils.TestAmounts.FIVE;
import static com.machines.vending.utils.TestAmounts.TEN;
import static com.machines.vending.utils.TestAmounts.THREE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateProductCommandImplTest {

    private CreateProductCommand createProductCommand;

    @Mock
    private ProductRepository productRepository;

    private String productName;
    private Integer id;
    private int sellerId;
    private int cost;
    private int amountAvailable;

    @BeforeEach
    void setUp() {
        createProductCommand = new CreateProductCommandImpl(productRepository);
        id = new Random().nextInt();
        productName = "Fresh Water";
        sellerId = 10;
        cost = FIVE;
        amountAvailable = TEN;
    }

    @Test
    void createProduct() throws NotValidProductCostException, NotValidProductNameException, CreateProductWithGivenIdException {
        //given
        final Product productToCreate = Product.builder()
                .productName(productName).sellerId(sellerId).cost(cost).amountAvailable(amountAvailable).build();
        when(productRepository.save(any())).thenReturn(ProductEntity.builder().id(id).build());

        //when
        final Product createdProduct = createProductCommand.execute(productToCreate);

        //then
        assertThat(createdProduct.getId()).isEqualTo(id);
        final ArgumentCaptor<ProductEntity> productEntityCaptor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(productRepository).save(productEntityCaptor.capture());
        final ProductEntity productEntity = productEntityCaptor.getValue();
        assertThat(productEntity.getId()).isNull();
        assertThat(productEntity.getProductName()).isEqualTo(productName);
        assertThat(productEntity.getSellerId()).isEqualTo(sellerId);
        assertThat(productEntity.getCost()).isEqualTo(cost);
        assertThat(productEntity.getAmountAvailable()).isEqualTo(amountAvailable);
    }

    @Test
    void shouldThrowsNotValidCostException_whenCostIsNotMultipleOfFive() {
        // given
        final Product productToCreate = Product.builder().cost(THREE).build();

        // when
        // then
        assertThrows(NotValidProductCostException.class, () -> createProductCommand.execute(productToCreate));
    }

    @Test
    void shouldThrowsNotValidNameException_whenNameIsNullOrEmpty() {
        // given
        // when
        final Product productToCreate = Product.builder().productName("").build();

        // then
        assertThrows(NotValidProductNameException.class, () -> createProductCommand.execute(productToCreate));
    }

    @Test
    void shouldThrowsCreateProductWithGivenIdException_whenReceivedProductInformationIncludesNotNullId() {
        // given
        // when
        final Product productToCreate = Product.builder().id(id).productName("").build();

        // then
        assertThrows(CreateProductWithGivenIdException.class, () -> createProductCommand.execute(productToCreate));
    }
}
