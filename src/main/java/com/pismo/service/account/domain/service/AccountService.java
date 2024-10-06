package com.pismo.service.account.domain.service;


import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.repository.AccountRepository;
import com.pismo.service.account.domain.validators.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountValidator accountValidator;

    public Account save(Account account){
        accountValidator.validate(account);
        return accountRepository.save(account);
    }

    public Account findById(Integer accountId){
        Account account =  accountRepository.findByAccountId(accountId);

        if (account == null){
            throw new NotFoundException("invalid account id");
        }

        return account;
    }
}
