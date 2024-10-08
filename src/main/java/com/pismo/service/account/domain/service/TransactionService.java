package com.pismo.service.account.domain.service;

import com.pismo.service.account.application.controller.TransactionController;
import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.domain.entities.enums.OperationType;
import com.pismo.service.account.domain.repository.AccountRepository;
import com.pismo.service.account.domain.repository.TransactionRepository;
import com.pismo.service.account.domain.validators.TransactionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionValidator transactionValidator;

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public Transaction create(Transaction transaction) {
        logger.info("Validanting transaction");
        transactionValidator.validate(transaction);
        logger.info("transaction validated");
        return transactionRepository.create(amountByType(transaction));
    }

    public Double balance(Integer accountId){
        return transactionRepository.balance(accountId);
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
