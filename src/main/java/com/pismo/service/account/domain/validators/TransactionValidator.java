package com.pismo.service.account.domain.validators;

import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.domain.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

@Component
public class TransactionValidator {

    @Autowired
    private AccountRepository accountRepository;

    private static final Logger logger = LoggerFactory.getLogger(TransactionValidator.class);

    public void validate(Transaction transaction){
        exists(transaction);
    }

    private void exists(Transaction transaction){
        if(!accountRepository.existsByAccountId(transaction.getAccount().getAccountId())){
            logger.error("Account {} not found", transaction.getAccount().getAccountId());
            throw new NotFoundException("Account not found");
        }
    }
}
