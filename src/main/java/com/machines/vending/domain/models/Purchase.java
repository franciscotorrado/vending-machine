package com.machines.vending.domain.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Purchase extends Model {
    final int productId;
    final int amount;
}
