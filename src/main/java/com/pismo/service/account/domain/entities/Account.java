package com.pismo.service.account.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer accountId;
    private String documentNumber;
    private List<Transaction> transactions;
}
