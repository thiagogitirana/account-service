package com.pismo.service.account.infrastructure.repository.adapters;

import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.domain.entities.enums.OperationType;
import com.pismo.service.account.infrastructure.repository.model.AccountJPA;
import com.pismo.service.account.infrastructure.repository.model.OperationTypeJPA;
import com.pismo.service.account.infrastructure.repository.model.TransactionJPA;
import com.pismo.service.account.utils.AccountBuilder;
import com.pismo.service.account.utils.OperationTypeBuilder;
import com.pismo.service.account.utils.TransactionBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionAdapterTest {

    @Mock
    private OperationTypeAdapter operationTypeAdapter;

    @Mock
    private AccountAdapter accountAdapter;

    @InjectMocks
    private TransactionAdapter transactionAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldConvertDomainToJPA() {
        Transaction transaction = TransactionBuilder.buildDefaultTransaction();
        Account account = AccountBuilder.buildDeafultAccount();
        AccountJPA accountJPA = AccountBuilder.buildDeafultAccountJPA();
        OperationTypeJPA operationTypeJPA = OperationTypeBuilder.buildDefaultOperationTypeJpa();
        operationTypeJPA.setDescription("PURCHASE");

        when(accountAdapter.toJPA(account)).thenReturn(accountJPA);
        when(operationTypeAdapter.toJpa(OperationType.PURCHASE)).thenReturn(operationTypeJPA);

        TransactionJPA transactionJPA = transactionAdapter.toJpa(transaction);

        assertNotNull(transactionJPA);
        assertEquals(100, transactionJPA.getTransactionId());
        assertEquals(100.0, transactionJPA.getAmount());
        assertEquals(accountJPA, transactionJPA.getAccount());
        assertEquals(operationTypeJPA, transactionJPA.getOperationType());

        verify(accountAdapter, times(1)).toJPA(account);
        verify(operationTypeAdapter, times(1)).toJpa(OperationType.PURCHASE);
    }

    @Test
    public void shouldConvertJPAToDomain() {
        TransactionJPA transactionJPA = TransactionBuilder.buildDefaultTransactionJpa();
        AccountJPA accountJPA = AccountBuilder.buildDeafultAccountJPA();
        OperationTypeJPA operationTypeJPA = OperationTypeBuilder.buildDefaultOperationTypeJpa();
        Account account = AccountBuilder.buildDeafultAccount();

        when(accountAdapter.toDomain(accountJPA, null)).thenReturn(account);
        when(operationTypeAdapter.toDomain(operationTypeJPA)).thenReturn(OperationType.PAYMENT);

        Transaction transaction = transactionAdapter.toDomain(transactionJPA);

        assertNotNull(transaction);
        assertEquals(100, transaction.getTransactionId());
        assertEquals(100.0, transaction.getAmount());
        assertEquals(account, transaction.getAccount());
        assertEquals(OperationType.PAYMENT, transaction.getOperationType());

        verify(accountAdapter, times(1)).toDomain(accountJPA, null);
        verify(operationTypeAdapter, times(1)).toDomain(operationTypeJPA);
    }
}
