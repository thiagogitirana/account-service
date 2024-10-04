package com.pismo.service.account.domain.repository;

import com.pismo.service.account.domain.entities.Account;

public interface AccountRepository {
    Account save(Account account);
    Account findByAccountId(Integer accountId);
}
