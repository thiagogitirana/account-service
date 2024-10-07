package com.pismo.service.account.domain.service;


import com.pismo.service.account.application.controller.AccountController;
import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.repository.AccountRepository;
import com.pismo.service.account.domain.validators.AccountValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountValidator accountValidator;

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    public Account save(Account account){
        logger.info("Validanting account");
        accountValidator.validate(account);
        return accountRepository.save(account);
    }

    public Account findById(Integer accountId){
        logger.info("searching for account by id");
        Account account =  accountRepository.findByAccountId(accountId);

        if (account == null){
            logger.error("invalid account id: {}", accountId);
            throw new NotFoundException("invalid account id");
        }

        return account;
    }
}
