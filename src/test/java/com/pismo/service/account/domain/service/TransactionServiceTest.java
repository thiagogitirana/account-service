package com.pismo.service.account.domain.service;

import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.domain.entities.enums.OperationType;
import com.pismo.service.account.domain.repository.TransactionRepository;
import com.pismo.service.account.domain.validators.TransactionValidator;
import com.pismo.service.account.utils.TransactionBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionValidator transactionValidator;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSuccessfullyValidateAndCreateTransactionWhenGivenAValidTransaction() {

        Transaction transaction = TransactionBuilder.buildDefaultTransaction();

        when(transactionRepository.create(any(Transaction.class))).thenReturn(transaction);

        Transaction createdTransaction = transactionService.create(transaction);

        assertNotNull(createdTransaction);
        assertEquals(-100.0, createdTransaction.getAmount());
        verify(transactionValidator, times(1)).validate(transaction);
        verify(transactionRepository, times(1)).create(any(Transaction.class));
    }

    @Test
    public void shouldHandlePaymentOperationTypeWhenGivenANegativeAmount() {

        Transaction transaction = new Transaction();
        transaction.setAmount(-100.0);
        transaction.setOperationType(OperationType.PAYMENT);

        when(transactionRepository.create(any(Transaction.class))).thenReturn(transaction);

        Transaction createdTransaction = transactionService.create(transaction);

        assertNotNull(createdTransaction);
        assertEquals(100.0, createdTransaction.getAmount());  // Verifica se o valor foi ajustado para positivo
        verify(transactionValidator, times(1)).validate(transaction);
        verify(transactionRepository, times(1)).create(any(Transaction.class));
    }

    @Test
    public void shouldNotChangeAmountForNegativePurchaseWhenGivenTheRight() {

        Transaction transaction = new Transaction();
        transaction.setAmount(-100.0);
        transaction.setOperationType(OperationType.PURCHASE);

        when(transactionRepository.create(any(Transaction.class))).thenReturn(transaction);

        Transaction createdTransaction = transactionService.create(transaction);

        assertNotNull(createdTransaction);
        assertEquals(-100.0, createdTransaction.getAmount());  // O valor já é negativo, então não deve mudar
        verify(transactionValidator, times(1)).validate(transaction);
        verify(transactionRepository, times(1)).create(any(Transaction.class));
    }
}
