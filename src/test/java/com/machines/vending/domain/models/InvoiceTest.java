package com.machines.vending.domain.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.machines.vending.utils.TestAmounts.FIVE;
import static com.machines.vending.utils.TestAmounts.FOUR;
import static com.machines.vending.utils.TestAmounts.TWENTY;
import static org.assertj.core.api.Assertions.assertThat;

class InvoiceTest {

    private String productName;
    private int amount;
    private int cost;
    private int total;

    @BeforeEach
    void setUp() {
        productName = "Product 1";
        cost = FIVE;
        amount = FOUR;
        total = TWENTY;
    }

    @Test
    void constructor() {
        //given
        final Invoice invoice = Invoice.builder().productName(productName)
                .amount(amount).cost(cost).total(total).build();
        //when
        //then
        assertThat(invoice.getProductName()).isEqualTo(productName);
        assertThat(invoice.getAmount()).isEqualTo(amount);
        assertThat(invoice.getCost()).isEqualTo(cost);
        assertThat(invoice.getTotal()).isEqualTo(total);
    }
}
