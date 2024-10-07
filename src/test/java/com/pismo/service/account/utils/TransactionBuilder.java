package com.pismo.service.account.utils;

import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.domain.entities.enums.OperationType;
import com.pismo.service.account.infrastructure.repository.model.TransactionJPA;

public class TransactionBuilder {
    public static Transaction buildDefaultTransaction (){
        Transaction transaction = new Transaction();
        transaction.setTransactionId(100);
        transaction.setAccount(AccountBuilder.buildDeafultAccount());
        transaction.setOperationType(OperationType.PURCHASE);
        transaction.setAmount(100.0);

        return transaction;
    }

    public static TransactionJPA buildDefaultTransactionJpa(){
        TransactionJPA transactionJPA = new TransactionJPA();
        transactionJPA.setTransactionId(100);
        transactionJPA.setAccount(AccountBuilder.buildDeafultAccountJPA());
        transactionJPA.setOperationType(OperationTypeBuilder.buildDefaultOperationTypeJpa());
        transactionJPA.setAmount(100.00);

        return transactionJPA;
    }
}
