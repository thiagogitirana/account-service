package com.pismo.service.account.infrastructure.repository.adapters;

import com.pismo.service.account.domain.entities.enums.OperationType;
import com.pismo.service.account.infrastructure.repository.model.OperationTypeJPA;
import org.springframework.stereotype.Component;

@Component
public class OperationTypeAdapter {

    public OperationTypeJPA toJpa(OperationType operationType) {
        OperationTypeJPA operationTypeJPA = new OperationTypeJPA();
        operationTypeJPA.setOperationTypeId(operationType.getOperationTypeId());
        operationTypeJPA.setDescription(operationType.getDescription());
        return operationTypeJPA;
    }

    public OperationType toDomain(OperationTypeJPA operationTypeJPA) {
        return OperationType.valueOf(operationTypeJPA.getDescription());
    }
}
