package com.pismo.service.account.application.adapters;

import com.pismo.service.account.application.dto.AccountRequestDTO;
import com.pismo.service.account.application.dto.AccountResponseDTO;
import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.utils.AccountBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class AccountAdapterTest {
    private AccountAdapter accountAdapter;

    @BeforeEach
    public void setUp() {
        accountAdapter = new AccountAdapter();
    }

    @Test
    public void shouldReturnAccountDTOWhenGivenAValidAccount() {

        Account account = AccountBuilder.buildDeafultAccount();

        AccountResponseDTO responseDTO = accountAdapter.toResponseDTO(account, Collections.emptyList());

        assertNotNull(responseDTO);
        assertEquals(1, responseDTO.accountId());
        assertEquals("123456789", responseDTO.documentNumber());
    }

    @Test
    public void shouldReturnAccountWhenGivenAValidAccountDTO() {
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO("987654321");

        Account account = accountAdapter.toDomain(accountRequestDTO);

        assertNotNull(account);
        assertEquals("987654321", account.getDocumentNumber());
    }
}
