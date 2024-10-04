package com.pismo.service.account.domain.entities.enums;

import lombok.Getter;

@Getter
public enum OperationType {
    PURCHASE(1, "PURCHASE"),
    INSTALLMENT_PURCHASE(2, "INSTALLMENT PURCHASE"),
    WITHDRAWAL(3, "WITHDRAWAL"),
    PAYMENT(4, "PAYMENT");


    private Integer operationTypeId;
    private String description;

    OperationType(Integer operationTypeId, String description) {
        this.operationTypeId = operationTypeId;
        this.description = description;
    }
}
