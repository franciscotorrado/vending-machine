package com.machines.vending.domain.commands.product;

import com.machines.vending.domain.models.ProductItem;
import com.machines.vending.infrastructure.persistence.entities.ProductEntity;
import com.machines.vending.infrastructure.persistence.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Random;

import static com.machines.vending.domain.models.Role.BUYER;
import static com.machines.vending.utils.TestAmounts.TWENTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadAllProductsCommandImplTest {

    private ReadAllProductsCommand readAllProductsCommand;

    @Mock
    private ProductRepository productRepository;

    private int id;
    private int userId;
    private String productName;

    @BeforeEach
    void setUp() {
        readAllProductsCommand = new ReadAllProductCommandImpl(productRepository);
        id = new Random().nextInt();
        userId = new Random().nextInt();
        productName = "Fresh Water";
    }

    @Test
    void readProduct() {
        //given
        when(productRepository.findAll()).thenReturn(
                List.of(ProductEntity.builder().id(id).productName(productName)
                        .cost(TWENTY).sellerId(10).amountAvailable(12).build()));

        //when
        final List<ProductItem> storedProducts = readAllProductsCommand.execute(userId, BUYER);

        //then
        assertThat(storedProducts.size()).isEqualTo(1);
        assertThat(storedProducts.get(0).getId()).isEqualTo(id);
        assertThat(storedProducts.get(0).getProductName()).isEqualTo(productName);
        assertThat(storedProducts.get(0).getAmountAvailable()).isEqualTo(12);
        assertThat(storedProducts.get(0).getCost()).isEqualTo(TWENTY);
    }
}
