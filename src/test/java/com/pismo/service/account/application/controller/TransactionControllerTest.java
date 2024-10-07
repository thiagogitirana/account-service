package com.pismo.service.account.application.controller;

import com.pismo.service.account.application.adapters.TransactionAdapter;
import com.pismo.service.account.application.dto.TransactionRequestDTO;
import com.pismo.service.account.application.dto.TransactionResponseDTO;
import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.domain.service.TransactionService;
import com.pismo.service.account.utils.TransactionBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionControllerTest {

    @Mock
    private TransactionAdapter transactionAdapter;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldRReturnTransactionResponseWhenGivenAValidRequest() {
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(1, 1, 100.0);
        Transaction transaction = TransactionBuilder.buildDefaultTransaction();
        Transaction savedTransaction = TransactionBuilder.buildDefaultTransaction();

        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO(1, 1, 1, 100.0);

        when(transactionAdapter.toDomain(transactionRequestDTO)).thenReturn(transaction);
        when(transactionService.create(transaction)).thenReturn(savedTransaction);
        when(transactionAdapter.toResponseDto(savedTransaction)).thenReturn(transactionResponseDTO);

        ResponseEntity<TransactionResponseDTO> response = transactionController.save(transactionRequestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().transactionId());
        assertEquals(1, response.getBody().accountId());
        assertEquals(1, response.getBody().operationTypeId());
        assertEquals(100.0, response.getBody().amount());

        verify(transactionAdapter, times(1)).toDomain(transactionRequestDTO);
        verify(transactionService, times(1)).create(transaction);
        verify(transactionAdapter, times(1)).toResponseDto(savedTransaction);
    }
}
