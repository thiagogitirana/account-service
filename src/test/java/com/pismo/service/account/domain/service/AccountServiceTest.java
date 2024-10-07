package com.pismo.service.account.domain.service;

import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.repository.AccountRepository;
import com.pismo.service.account.domain.validators.AccountValidator;
import com.pismo.service.account.utils.AccountBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.webjars.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountValidator accountValidator;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSuccessfullyValidateAndSaveAccountWhenGivenAValidAccount() {

        Account account = AccountBuilder.buildDeafultAccount();

        when(accountRepository.save(account)).thenReturn(account);

        Account savedAccount = accountService.save(account);

        assertNotNull(savedAccount);
        assertEquals(1, savedAccount.getAccountId());
        assertEquals("123456789", savedAccount.getDocumentNumber());

        verify(accountValidator, times(1)).validate(account);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void shouldReturnAccountWhenExists() {

        Account account = AccountBuilder.buildDeafultAccount();

        when(accountRepository.findByAccountId(1)).thenReturn(account);

        Account foundAccount = accountService.findById(1);

        assertNotNull(foundAccount);
        assertEquals(1, foundAccount.getAccountId());
        assertEquals("123456789", foundAccount.getDocumentNumber());

        verify(accountRepository, times(1)).findByAccountId(1);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenAccountDoesNotExist() {

        when(accountRepository.findByAccountId(1)).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> accountService.findById(1));
        assertEquals("invalid account id", exception.getMessage());

        verify(accountRepository, times(1)).findByAccountId(1);
    }
}
