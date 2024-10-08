package com.pismo.service.account.infrastructure.repository.impl;

import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.domain.repository.TransactionRepository;
import com.pismo.service.account.infrastructure.repository.TransactionRepositoryJPA;
import com.pismo.service.account.infrastructure.repository.adapters.TransactionAdapter;
import com.pismo.service.account.infrastructure.repository.model.TransactionJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionRepositoryImpl implements TransactionRepository {

    @Autowired
    private TransactionRepositoryJPA transactionRepositoryJPA;
    @Autowired
    private TransactionAdapter transactionAdapter;

    private static final Logger logger = LoggerFactory.getLogger(TransactionRepositoryImpl.class);

    @Override
    public Transaction create(Transaction transaction) {
        logger.info("saving transaction on database");
        TransactionJPA transactionJPA = transactionAdapter.toJpa(transaction);
        TransactionJPA saved = transactionRepositoryJPA.save(transactionJPA);
        logger.info("Transaction saved: {}", saved);
        return transactionAdapter.toDomain(saved);
    }

    @Override
    public Double balance(Integer accountId) {
        return transactionRepositoryJPA.balance(accountId);
    }
}
