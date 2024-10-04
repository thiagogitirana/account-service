package com.pismo.service.account.domain.entities;

import com.pismo.service.account.domain.entities.enums.OperationType;

public record Transaction(
        Account account,
        OperationType operationType,
        Double amount
) {
}
