package com.machines.vending.domain.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Invoice extends Model {
    final String productName;
    final int amount;
    final int cost;
    final int total;
}
