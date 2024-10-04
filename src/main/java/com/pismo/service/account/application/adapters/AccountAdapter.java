package com.pismo.service.account.application.adapters;

import com.pismo.service.account.application.dto.AccountRequestDTO;
import com.pismo.service.account.application.dto.AccountResponseDTO;
import com.pismo.service.account.domain.entities.Account;
import org.springframework.stereotype.Component;

@Component("AccountAdapterApplication")
public class AccountAdapter {

    public AccountResponseDTO toResponseDTO(Account account) {
        return new AccountResponseDTO(account.getAccountId(), account.getDocumentNumber());
    }

    public Account toDomain(AccountRequestDTO accountRequestDTO) {
        Account account = new Account();
        account.setDocumentNumber(accountRequestDTO.documentNumber());
        return account;
    }

}
