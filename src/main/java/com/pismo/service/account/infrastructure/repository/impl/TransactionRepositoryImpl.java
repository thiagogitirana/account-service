package com.pismo.service.account.infrastructure.repository.impl;

import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.domain.repository.TransactionRepository;
import com.pismo.service.account.infrastructure.repository.TransactionRepositoryJPA;
import com.pismo.service.account.infrastructure.repository.adapters.TransactionAdapter;
import com.pismo.service.account.infrastructure.repository.model.TransactionJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionRepositoryImpl implements TransactionRepository {

    @Autowired
    private TransactionRepositoryJPA transactionRepositoryJPA;
    @Autowired
    private TransactionAdapter transactionAdapter;

    @Override
    public Transaction create(Transaction transaction) {
        TransactionJPA transactionJPA = transactionAdapter.toJpa(transaction);
        TransactionJPA saved = transactionRepositoryJPA.save(transactionJPA);
        return transactionAdapter.toDomain(saved);
    }
}
