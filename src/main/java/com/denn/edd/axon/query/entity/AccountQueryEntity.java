package com.denn.edd.axon.query.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class AccountQueryEntity {
    @Id
    private String id;
    private double accountBalance;

    private String currency;

    private String status;
}
