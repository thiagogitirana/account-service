package com.pismo.service.account.domain.validators;

import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.domain.repository.AccountRepository;
import com.pismo.service.account.domain.repository.TransactionRepository;
import com.pismo.service.account.utils.AccountBuilder;
import com.pismo.service.account.utils.TransactionBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.webjars.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionValidatorTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionValidator transactionValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldPassWhenAccountExists() {

        Transaction transaction = TransactionBuilder.buildDefaultTransaction();
        Account account = AccountBuilder.buildDeafultAccount();

        when(accountRepository.existsByAccountId(1)).thenReturn(true);
        when(accountRepository.findByAccountId(1)).thenReturn(account);
        when(transactionRepository.balance(1)).thenReturn(10000.0);

        assertDoesNotThrow(() -> transactionValidator.validate(transaction));
        verify(accountRepository, times(1)).existsByAccountId(1);
        verify(accountRepository, times(1)).findByAccountId(1);
        verify(transactionRepository, times(1)).balance(1);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenAccountDoesNotExist() {

        Transaction transaction = TransactionBuilder.buildDefaultTransaction();

        when(accountRepository.existsByAccountId(1)).thenReturn(false);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> transactionValidator.validate(transaction));
        assertEquals("Account not found", exception.getMessage());

        verify(accountRepository, times(1)).existsByAccountId(1);
    }
}
