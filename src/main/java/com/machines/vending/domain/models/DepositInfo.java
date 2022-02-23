package com.machines.vending.domain.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DepositInfo extends Model {
    int availableAmount;
}
