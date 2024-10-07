package com.pismo.service.account.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountRequestDTOTest {

    @Test
    public void shouldCreateAccountRequestDTOWhenDocumentNumberIsValid() {

        AccountRequestDTO accountRequestDTO = new AccountRequestDTO("123456789");

        assertNotNull(accountRequestDTO);
        assertEquals("123456789", accountRequestDTO.documentNumber());
    }

    @Test
    public void shouldThrowExceptionWhenDocumentNumberIsBlank() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new AccountRequestDTO(""));
        assertEquals("Document id is required", exception.getMessage());
    }

}
