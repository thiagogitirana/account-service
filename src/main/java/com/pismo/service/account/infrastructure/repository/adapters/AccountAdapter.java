package com.pismo.service.account.infrastructure.repository.adapters;

import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.infrastructure.repository.model.AccountJPA;
import org.springframework.stereotype.Component;

@Component
public class AccountAdapter {

    public AccountJPA toJPA(Account account) {
        AccountJPA accountJPA = new AccountJPA();
        accountJPA.setAccountId(account.getAccountId());
        accountJPA.setDocumentNumber(account.getDocumentNumber());
        return accountJPA;
    }

    public Account toDomain(AccountJPA accountJPA) {
        return new Account(
                accountJPA.getAccountId(),
                accountJPA.getDocumentNumber()
        );
    }

}
