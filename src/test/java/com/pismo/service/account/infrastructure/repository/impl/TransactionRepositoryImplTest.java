package com.pismo.service.account.infrastructure.repository.impl;

import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.infrastructure.repository.TransactionRepositoryJPA;
import com.pismo.service.account.infrastructure.repository.adapters.TransactionAdapter;
import com.pismo.service.account.infrastructure.repository.model.TransactionJPA;
import com.pismo.service.account.utils.TransactionBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionRepositoryImplTest {

    @Mock
    private TransactionRepositoryJPA transactionRepositoryJPA;

    @Mock
    private TransactionAdapter transactionAdapter;

    @InjectMocks
    private TransactionRepositoryImpl transactionRepositoryImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void create_ShouldSaveTransactionAndReturnDomain() {

        Transaction transaction = TransactionBuilder.buildDefaultTransaction();

        TransactionJPA transactionJPA = TransactionBuilder.buildDefaultTransactionJpa();

        when(transactionAdapter.toJpa(transaction)).thenReturn(transactionJPA);
        when(transactionRepositoryJPA.save(transactionJPA)).thenReturn(transactionJPA);
        when(transactionAdapter.toDomain(transactionJPA)).thenReturn(transaction);

        Transaction savedTransaction = transactionRepositoryImpl.create(transaction);

        assertNotNull(savedTransaction);
        assertEquals(100, savedTransaction.getTransactionId());
        assertEquals(100.0, savedTransaction.getAmount());

        verify(transactionAdapter, times(1)).toJpa(transaction);
        verify(transactionRepositoryJPA, times(1)).save(transactionJPA);
        verify(transactionAdapter, times(1)).toDomain(transactionJPA);
    }
}
