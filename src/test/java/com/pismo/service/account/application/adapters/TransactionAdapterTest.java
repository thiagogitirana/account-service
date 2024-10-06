package com.pismo.service.account.application.adapters;

import com.pismo.service.account.application.dto.TransactionRequestDTO;
import com.pismo.service.account.application.dto.TransactionResponseDTO;
import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.domain.entities.enums.OperationType;
import com.pismo.service.account.utils.TransactionBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionAdapterTest {

    @Mock
    private AccountAdapter accountAdapter;

    @InjectMocks
    private TransactionAdapter transactionAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnTransactionDTOWhenGivenAValidTransaction() {
        Account account = new Account();
        account.setAccountId(1);

        Transaction transaction = TransactionBuilder.buildDefaultTransaction();

        TransactionResponseDTO responseDTO = transactionAdapter.toResponseDto(transaction);

        assertNotNull(responseDTO);
        assertEquals(100, responseDTO.transactionId());
        assertEquals(1, responseDTO.accountId());
        assertEquals(OperationType.PURCHASE.getOperationTypeId(), responseDTO.operationTypeId());
        assertEquals(100.0, responseDTO.amount());
    }

    @Test
    public void shouldReturnTransactionWhenGivenAValidTransactionDTO() {
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(1, 1, 500.0);

        Transaction transaction = transactionAdapter.toDomain(transactionRequestDTO);

        assertNotNull(transaction);
        assertEquals(1, transaction.getAccount().getAccountId());
        assertEquals(OperationType.PURCHASE, transaction.getOperationType());
        assertEquals(500.0, transaction.getAmount());
    }

    @Test
    public void shouldThrowExceptionWhenGivenAInvalidTransaction() {
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(1, 999, 500.0);

        assertThrows(IllegalArgumentException.class, () -> transactionAdapter.toDomain(transactionRequestDTO));
    }
}
