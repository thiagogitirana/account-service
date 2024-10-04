package com.pismo.service.account.domain.service;


import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account save(Account account){
        return accountRepository.save(account);
    }

    public Account findById(Integer accountId){
        return accountRepository.findByAccountId(accountId);
    }
}
