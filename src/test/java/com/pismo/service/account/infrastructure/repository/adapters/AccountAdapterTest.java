package com.pismo.service.account.infrastructure.repository.adapters;

import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.infrastructure.repository.model.AccountJPA;
import com.pismo.service.account.utils.AccountBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountAdapterTest {

    private AccountAdapter accountAdapter;

    @BeforeEach
    public void setUp() {
        accountAdapter = new AccountAdapter();
    }

    @Test
    public void shouldConvertDomainToJPAWhenGivenAValidAccount() {

        Account account = AccountBuilder.buildDeafultAccount();

        AccountJPA accountJPA = accountAdapter.toJPA(account);

        assertNotNull(accountJPA);
        assertEquals(1, accountJPA.getAccountId());
        assertEquals("123456789", accountJPA.getDocumentNumber());
    }

    @Test
    public void shouldConvertJPAToDomainWhenGivenAValidAccountJPA() {

        AccountJPA accountJPA = AccountBuilder.buildDeafultAccountJPA();

        Account account = accountAdapter.toDomain(accountJPA);

        assertNotNull(account);
        assertEquals(1, account.getAccountId());
        assertEquals("123456789", account.getDocumentNumber());
    }
}
