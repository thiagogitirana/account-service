package com.pismo.service.account.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionRequestDTOTest {

    @Test
    public void shouldCreateTransactionRequestDTOWhenAllFieldsAreValid() {
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(1, 1, 100.0);

        assertNotNull(transactionRequestDTO);
        assertEquals(1, transactionRequestDTO.accountId());
        assertEquals(1, transactionRequestDTO.operationTypeId());
        assertEquals(100.0, transactionRequestDTO.amount());
    }

    @Test
    public void shouldThrowExceptionWhenAccountIdIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new TransactionRequestDTO(null, 1, 100.0));
        assertEquals("account id is required", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenOperationTypeIdIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new TransactionRequestDTO(1, null, 100.0));
        assertEquals("operation type id is required", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenAmountIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new TransactionRequestDTO(1, 1, null));
        assertEquals("amount is required", exception.getMessage());
    }
}
