package com.pismo.service.account.domain.repository;

import com.pismo.service.account.domain.entities.Transaction;

public interface TransactionRepository {
    Transaction create(Transaction transaction);
    Double balance(Integer accountId);
}
