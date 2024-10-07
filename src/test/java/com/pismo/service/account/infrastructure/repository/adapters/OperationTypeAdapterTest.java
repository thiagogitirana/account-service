package com.pismo.service.account.infrastructure.repository.adapters;

import com.pismo.service.account.domain.entities.enums.OperationType;
import com.pismo.service.account.infrastructure.repository.model.OperationTypeJPA;
import com.pismo.service.account.utils.OperationTypeBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OperationTypeAdapterTest {

    private OperationTypeAdapter operationTypeAdapter;

    @BeforeEach
    public void setUp() {
        operationTypeAdapter = new OperationTypeAdapter();
    }

    @Test
    public void shouldConvertDomainToJPA() {

        OperationType operationType = OperationType.PAYMENT;

        OperationTypeJPA operationTypeJPA = operationTypeAdapter.toJpa(operationType);

        assertNotNull(operationTypeJPA);
        assertEquals(operationType.getOperationTypeId(), operationTypeJPA.getOperationTypeId());
        assertEquals(operationType.getDescription(), operationTypeJPA.getDescription());
    }

    @Test
    public void shouldConvertJPAToDomain() {

        OperationTypeJPA operationTypeJPA = OperationTypeBuilder.buildDefaultOperationTypeJpa();

        OperationType operationType = operationTypeAdapter.toDomain(operationTypeJPA);

        assertNotNull(operationType);
        assertEquals(OperationType.PAYMENT, operationType);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForInvalidDescription() {

        OperationTypeJPA operationTypeJPA = new OperationTypeJPA();
        operationTypeJPA.setDescription("INVALID_OPERATION");

        assertThrows(IllegalArgumentException.class, () -> operationTypeAdapter.toDomain(operationTypeJPA));
    }
}
