package com.pismo.service.account.application.adapters;

import com.pismo.service.account.application.dto.TransactionRequestDTO;
import com.pismo.service.account.application.dto.TransactionResponseDTO;
import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.domain.entities.enums.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("TransactionAdapterApplication")
public class TransactionAdapter {
    @Autowired
    private AccountAdapter accountAdapter;

    public TransactionResponseDTO toResponseDto(Transaction transaction) {
        return new TransactionResponseDTO(transaction.getTransactionId(), transaction.getAccount().getAccountId(),
                transaction.getOperationType().getOperationTypeId(), transaction.getAmount());
    }

    public Transaction toDomain(TransactionRequestDTO transactionRequestDTO){
        Transaction transaction = new Transaction();
        Account account = new Account();
        account.setAccountId(transactionRequestDTO.accountId());
        transaction.setAccount(account);
        transaction.setOperationType(OperationType.fromOperationTypeId(transactionRequestDTO.operationTypeId()));
        transaction.setAmount(transactionRequestDTO.amount());
        return transaction;
    }
}
