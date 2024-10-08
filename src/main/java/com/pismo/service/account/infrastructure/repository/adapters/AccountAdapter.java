package com.pismo.service.account.infrastructure.repository.adapters;

import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.infrastructure.repository.model.AccountJPA;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountAdapter {

    public AccountJPA toJPA(Account account) {
        AccountJPA accountJPA = new AccountJPA();
        accountJPA.setAccountId(account.getAccountId());
        accountJPA.setDocumentNumber(account.getDocumentNumber());
        accountJPA.setLimit(account.getLimit());
        return accountJPA;
    }

    public Account toDomain(AccountJPA accountJPA, List<Transaction> transactions) {
        Account account = new Account();
        account.setAccountId(accountJPA.getAccountId());
        account.setDocumentNumber(accountJPA.getDocumentNumber());
        account.setLimit(accountJPA.getLimit());
        account.setTransactions(transactions);

        return account;
    }

}
