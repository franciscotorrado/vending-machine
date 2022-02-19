package com.machines.vending.infraestructure.persistence.deposits;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class DepositEntity {
    @Id
    private Integer buyerId;
    private int value;
}
