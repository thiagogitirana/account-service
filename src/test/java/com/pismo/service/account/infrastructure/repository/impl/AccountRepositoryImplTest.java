package com.pismo.service.account.infrastructure.repository.impl;

import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.infrastructure.repository.AccountRepositoryJPA;
import com.pismo.service.account.infrastructure.repository.adapters.AccountAdapter;
import com.pismo.service.account.infrastructure.repository.model.AccountJPA;
import com.pismo.service.account.utils.AccountBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountRepositoryImplTest {

    @Mock
    private AccountRepositoryJPA accountRepositoryJPA;

    @Mock
    private AccountAdapter accountAdapter;

    @InjectMocks
    private AccountRepositoryImpl accountRepositoryImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSaveAccountAndReturnDomainWhenGivenAValidAccount() {

        Account account = AccountBuilder.buildDeafultAccount();
        AccountJPA accountJPA = AccountBuilder.buildDeafultAccountJPA();

        when(accountAdapter.toJPA(account)).thenReturn(accountJPA);
        when(accountRepositoryJPA.save(accountJPA)).thenReturn(accountJPA);
        when(accountAdapter.toDomain(accountJPA)).thenReturn(account);

        Account savedAccount = accountRepositoryImpl.save(account);

        assertNotNull(savedAccount);
        assertEquals(1, savedAccount.getAccountId());
        assertEquals("123456789", savedAccount.getDocumentNumber());

        verify(accountAdapter, times(1)).toJPA(account);
        verify(accountRepositoryJPA, times(1)).save(accountJPA);
        verify(accountAdapter, times(1)).toDomain(accountJPA);
    }

    @Test
    public void shouldReturnAccountWhenAccountExists() {

        int accountId = 1;

        AccountJPA accountJPA = AccountBuilder.buildDeafultAccountJPA();
        Account account = AccountBuilder.buildDeafultAccount();

        when(accountRepositoryJPA.findById(accountId)).thenReturn(Optional.of(accountJPA));
        when(accountAdapter.toDomain(accountJPA)).thenReturn(account);

        Account foundAccount = accountRepositoryImpl.findByAccountId(accountId);

        assertNotNull(foundAccount);
        assertEquals(accountId, foundAccount.getAccountId());
        assertEquals("123456789", foundAccount.getDocumentNumber());

        verify(accountRepositoryJPA, times(1)).findById(accountId);
        verify(accountAdapter, times(1)).toDomain(accountJPA);
    }

    @Test
    public void shouldReturnNullWhenAccountNotFound() {

        int accountId = 1;

        when(accountRepositoryJPA.findById(accountId)).thenReturn(Optional.empty());

        Account foundAccount = accountRepositoryImpl.findByAccountId(accountId);

        assertNull(foundAccount);

        verify(accountRepositoryJPA, times(1)).findById(accountId);
        verify(accountAdapter, never()).toDomain(any());
    }

    @Test
    public void shouldReturnTrueWhenAccountExists() {

        String documentNumber = "12345678900";
        when(accountRepositoryJPA.existsByDocumentNumber(documentNumber)).thenReturn(true);

        boolean exists = accountRepositoryImpl.existsByDocumentNumber(documentNumber);

        assertTrue(exists);

        verify(accountRepositoryJPA, times(1)).existsByDocumentNumber(documentNumber);
    }

    @Test
    public void shouldReturnFalseWhenAccountNotExists() {

        String documentNumber = "12345678900";
        when(accountRepositoryJPA.existsByDocumentNumber(documentNumber)).thenReturn(false);

        boolean exists = accountRepositoryImpl.existsByDocumentNumber(documentNumber);

        assertFalse(exists);

        verify(accountRepositoryJPA, times(1)).existsByDocumentNumber(documentNumber);
    }

    @Test
    public void shouldReturnTrueWhenAccountExistsById() {
        int accountId = 1;
        when(accountRepositoryJPA.existsById(accountId)).thenReturn(true);

        boolean exists = accountRepositoryImpl.existsByAccountId(accountId);

        assertTrue(exists);

        verify(accountRepositoryJPA, times(1)).existsById(accountId);
    }

    @Test
    public void shouldReturnFalseWhenAccountNotExistsById() {
        int accountId = 1;
        when(accountRepositoryJPA.existsById(accountId)).thenReturn(false);

        boolean exists = accountRepositoryImpl.existsByAccountId(accountId);

        assertFalse(exists);

        verify(accountRepositoryJPA, times(1)).existsById(accountId);
    }
}
