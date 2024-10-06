package com.pismo.service.account.utils;

import com.pismo.service.account.infrastructure.repository.model.OperationTypeJPA;

public class OperationTypeBuilder {
    public static OperationTypeJPA buildDefaultOperationTypeJpa() {
        OperationTypeJPA operationTypeJPA = new OperationTypeJPA();
        operationTypeJPA.setOperationTypeId(1);
        operationTypeJPA.setDescription("PAYMENT");
        return operationTypeJPA;
    }
}
