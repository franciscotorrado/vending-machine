package com.machines.vending.domain.models;

import com.machines.vending.domain.exceptions.product.NotEnoughProductAmountAvailableException;
import com.machines.vending.domain.exceptions.product.NotValidProductCostException;
import com.machines.vending.domain.exceptions.product.NotValidProductNameException;
import lombok.Builder;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Builder
@Getter
public class Product extends Model {
    private Integer id;
    private String productName;
    private int cost;
    private int amountAvailable;
    private int sellerId;

    public void validate() throws NotValidProductCostException, NotValidProductNameException {
        if (cost % Coin.FIVE.getValue() != 0) {
            throw new NotValidProductCostException();
        }
        if (Strings.isEmpty(productName)) {
            throw new NotValidProductNameException();
        }
    }

    public void reduceAvailableAmount(final int amount) throws NotEnoughProductAmountAvailableException {
        if (amount > amountAvailable) {
            throw new NotEnoughProductAmountAvailableException();
        }
        amountAvailable -= amount;
    }
}
