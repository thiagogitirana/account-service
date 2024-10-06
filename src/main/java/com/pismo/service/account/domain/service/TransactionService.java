package com.pismo.service.account.domain.service;

import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.domain.entities.enums.OperationType;
import com.pismo.service.account.domain.repository.TransactionRepository;
import com.pismo.service.account.domain.validators.TransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionValidator transactionValidator;

    public Transaction create(Transaction transaction) {
        transactionValidator.validate(transaction);
        return transactionRepository.create(amountByType(transaction));
    }

    private Transaction amountByType(Transaction transaction) {
        if (OperationType.PAYMENT.equals(transaction.getOperationType())) {
            transaction.setAmount(transaction.getAmount() < 0 ? Math.abs(transaction.getAmount()) : transaction.getAmount());
        } else {
            transaction.setAmount(transaction.getAmount() > 0 ? -transaction.getAmount() : transaction.getAmount());
        }
        return transaction;
    }
}
