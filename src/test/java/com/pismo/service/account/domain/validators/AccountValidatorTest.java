package com.pismo.service.account.domain.validators;

import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.exceptions.DuplicatedRecordException;
import com.pismo.service.account.domain.repository.AccountRepository;
import com.pismo.service.account.utils.AccountBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountValidatorTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountValidator accountValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldPassWhenAccountDoesNotExist() {

        Account account = AccountBuilder.buildDeafultAccount();

        when(accountRepository.existsByDocumentNumber("123456789")).thenReturn(false);

        assertDoesNotThrow(() -> accountValidator.validate(account));
        verify(accountRepository, times(1)).existsByDocumentNumber("123456789");
    }

    @Test
    public void shouldThrowDuplicatedRecordExceptionWhenAccountExists() {

        Account account = AccountBuilder.buildDeafultAccount();

        when(accountRepository.existsByDocumentNumber("123456789")).thenReturn(true);

        DuplicatedRecordException exception = assertThrows(DuplicatedRecordException.class, () -> accountValidator.validate(account));
        assertEquals("the document provided already has an account", exception.getMessage());

        verify(accountRepository, times(1)).existsByDocumentNumber("123456789");
    }
}
