package com.pismo.service.account.application.controller;

import com.pismo.service.account.application.adapters.AccountAdapter;
import com.pismo.service.account.application.dto.AccountRequestDTO;
import com.pismo.service.account.application.dto.AccountResponseDTO;
import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.service.AccountService;
import com.pismo.service.account.utils.AccountBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @Mock
    private AccountAdapter accountAdapter;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnAccountResponseWhenGivenAValidAccountRequest() {
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO("123456789");

        Account account = AccountBuilder.buildDeafultAccount();
        Account savedAccount = AccountBuilder.buildDeafultAccount();

        AccountResponseDTO accountResponseDTO = new AccountResponseDTO(1, "123456789", null);

        when(accountAdapter.toDomain(accountRequestDTO)).thenReturn(account);
        when(accountService.save(account)).thenReturn(savedAccount);
        when(accountAdapter.toResponseDTO(savedAccount, null)).thenReturn(accountResponseDTO);

        ResponseEntity<AccountResponseDTO> response = accountController.save(accountRequestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).accountId());
        assertEquals("123456789", response.getBody().documentNumber());

        verify(accountAdapter, times(1)).toDomain(accountRequestDTO);
        verify(accountService, times(1)).save(account);
        verify(accountAdapter, times(1)).toResponseDTO(savedAccount, null);
    }

    @Test
    public void shouldReturnAccountResponseWhenGivenAValidAccountId() {

        Account account = AccountBuilder.buildDeafultAccount();

        AccountResponseDTO accountResponseDTO = new AccountResponseDTO(1, "987654321", null);

        when(accountService.findById(1)).thenReturn(account);
        when(accountAdapter.toResponseDTO(account, null)).thenReturn(accountResponseDTO);

        ResponseEntity<AccountResponseDTO> response = accountController.findById(1);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).accountId());
        assertEquals("987654321", response.getBody().documentNumber());

        verify(accountService, times(1)).findById(1);
        verify(accountAdapter, times(1)).toResponseDTO(account, null);
    }
}
