package com.pismo.service.account.infrastructure.repository.impl;

import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.repository.AccountRepository;
import com.pismo.service.account.infrastructure.repository.AccountRepositoryJPA;
import com.pismo.service.account.infrastructure.repository.adapters.AccountAdapter;
import com.pismo.service.account.infrastructure.repository.model.AccountJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountRepositoryJPA accountRepositoryJPA;
    @Autowired
    private AccountAdapter accountAdapter;

    @Override
    public Account save(Account account) {
        AccountJPA accountJPA = accountAdapter.toJPA(account);
        accountJPA =  accountRepositoryJPA.save(accountJPA);
        return accountAdapter.toDomain(accountJPA);
    }

    @Override
    public Account findByAccountId(Integer accountId) {
        Optional<AccountJPA> accountJPA = accountRepositoryJPA.findById(accountId);

        return accountJPA.map(jpa -> accountAdapter.toDomain(jpa)).orElse(null);

    }
}
