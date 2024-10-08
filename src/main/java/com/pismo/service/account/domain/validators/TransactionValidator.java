package com.pismo.service.account.domain.validators;

import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.domain.entities.enums.OperationType;
import com.pismo.service.account.domain.exceptions.LimitException;
import com.pismo.service.account.domain.repository.AccountRepository;
import com.pismo.service.account.domain.repository.TransactionRepository;
import com.pismo.service.account.infrastructure.repository.model.AccountJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

@Component
public class TransactionValidator {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    private static final Logger logger = LoggerFactory.getLogger(TransactionValidator.class);

    public void validate(Transaction transaction){
        exists(transaction);
        validateAccountLimit(transaction);
    }

    private void exists(Transaction transaction){
        if(!accountRepository.existsByAccountId(transaction.getAccount().getAccountId())){
            logger.error("Account {} not found", transaction.getAccount().getAccountId());
            throw new NotFoundException("Account not found");
        }
    }

    private void validateAccountLimit(Transaction transaction){
        Account account = accountRepository.findByAccountId(transaction.getAccount().getAccountId());
        Double balance = transactionRepository.balance(account.getAccountId());
        Double transactionamout = (account.getLimit() + balance) - Math.abs(transaction.getAmount());
        if(!OperationType.PAYMENT.equals(transaction.getOperationType()) && transactionamout < 0){
            throw new LimitException("Amout transaction is bigger then limit");
        }
    }
}
