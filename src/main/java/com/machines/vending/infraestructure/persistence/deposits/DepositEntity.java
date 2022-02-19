package com.machines.vending.infraestructure.persistence.deposits;

import com.machines.vending.infraestructure.persistence.DBEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class DepositEntity extends DBEntity {
    private Integer buyerId;
    private int amount;
}
