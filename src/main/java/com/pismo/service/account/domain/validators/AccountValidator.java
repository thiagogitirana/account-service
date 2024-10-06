package com.pismo.service.account.domain.validators;

import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.exceptions.DuplicatedRecordException;
import com.pismo.service.account.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountValidator {

    @Autowired
    private AccountRepository accountRepository;

    public void validate(Account account){
        alreadyExists(account);
    }

    private void alreadyExists(Account account){
        if(accountRepository.existsByDocumentNumber(account.getDocumentNumber())){
            throw new DuplicatedRecordException("the document provided already has an account");
        }
    }
}
