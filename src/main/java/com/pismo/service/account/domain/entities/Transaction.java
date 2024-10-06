package com.pismo.service.account.domain.entities;

import com.pismo.service.account.domain.entities.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private Integer TransactionId;
    private Account account;
    private OperationType operationType;
    private Double amount;
}
