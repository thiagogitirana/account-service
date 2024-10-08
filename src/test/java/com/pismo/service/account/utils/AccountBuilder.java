package com.pismo.service.account.utils;

import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.infrastructure.repository.model.AccountJPA;

public class AccountBuilder {
    public static Account buildDeafultAccount(){
        Account account = new Account();
        account.setAccountId(1);
        account.setDocumentNumber("123456789");
        account.setLimit(0.0);
        return account;
    }

    public static AccountJPA buildDeafultAccountJPA(){
        AccountJPA accountJPA = new AccountJPA();
        accountJPA.setAccountId(1);
        accountJPA.setDocumentNumber("123456789");
        accountJPA.setLimit(0.0);
        return accountJPA;
    }
}
