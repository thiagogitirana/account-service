package com.pismo.service.account.infrastructure.repository.impl;

import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.repository.AccountRepository;
import com.pismo.service.account.domain.service.AccountService;
import com.pismo.service.account.infrastructure.repository.AccountRepositoryJPA;
import com.pismo.service.account.infrastructure.repository.adapters.AccountAdapter;
import com.pismo.service.account.infrastructure.repository.model.AccountJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountRepositoryJPA accountRepositoryJPA;
    @Autowired
    private AccountAdapter accountAdapter;

    private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryImpl.class);

    @Override
    public Account save(Account account) {
        logger.info("saving account on database");
        AccountJPA accountJPA = accountAdapter.toJPA(account);
        accountJPA =  accountRepositoryJPA.save(accountJPA);
        logger.info("Account saved: {}", accountJPA);
        return accountAdapter.toDomain(accountJPA);
    }

    @Override
    public Account findByAccountId(Integer accountId) {
        logger.info("searching for account on database");
        Optional<AccountJPA> accountJPA = accountRepositoryJPA.findById(accountId);

        return accountJPA.map(jpa -> accountAdapter.toDomain(jpa)).orElse(null);

    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        logger.info("verifying account by document number on database");
        return accountRepositoryJPA.existsByDocumentNumber(documentNumber);
    }

    @Override
    public boolean existsByAccountId(Integer accountId) {
        logger.info("verifying account by id on database");
        return accountRepositoryJPA.existsById(accountId);
    }
}
