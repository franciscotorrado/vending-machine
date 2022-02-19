package com.machines.vending.domain.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Product extends Model {
    private Integer id;
    private String productName;
    private int cost;
    private int amountAvailable;
    private int sellerId;
}
