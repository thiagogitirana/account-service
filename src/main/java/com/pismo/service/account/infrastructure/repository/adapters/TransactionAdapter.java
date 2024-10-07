package com.pismo.service.account.infrastructure.repository.adapters;

import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.infrastructure.repository.model.TransactionJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionAdapter {
    @Autowired
    private OperationTypeAdapter operationTypeAdapter;
    @Autowired
    private AccountAdapter accountAdapter;

    public TransactionJPA toJpa(Transaction transaction){
        TransactionJPA transactionJPA = new TransactionJPA();
        transactionJPA.setTransactionId(transaction.getTransactionId());
        transactionJPA.setOperationType(operationTypeAdapter.toJpa(transaction.getOperationType()));
        transactionJPA.setAccount(accountAdapter.toJPA(transaction.getAccount()));
        transactionJPA.setAmount(transaction.getAmount());

        return transactionJPA;
    }

    public Transaction toDomain(TransactionJPA transactionJPA){
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionJPA.getTransactionId());
        transaction.setAccount(accountAdapter.toDomain(transactionJPA.getAccount()));
        transaction.setOperationType(operationTypeAdapter.toDomain(transactionJPA.getOperationType()));
        transaction.setAmount(transactionJPA.getAmount());

        return transaction;
    }
}
