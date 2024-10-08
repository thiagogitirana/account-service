package com.pismo.service.account.application.adapters;

import com.pismo.service.account.application.dto.AccountRequestDTO;
import com.pismo.service.account.application.dto.AccountResponseDTO;
import com.pismo.service.account.application.dto.TransactionResponseDTO;
import com.pismo.service.account.domain.entities.Account;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("AccountAdapterApplication")
public class AccountAdapter {

    public AccountResponseDTO toResponseDTO(Account account, List<TransactionResponseDTO> transactions) {
        return new AccountResponseDTO(account.getAccountId(), account.getDocumentNumber(), transactions);
    }

    public Account toDomain(AccountRequestDTO accountRequestDTO) {
        Account account = new Account();
        account.setDocumentNumber(accountRequestDTO.documentNumber());
        return account;
    }

}
