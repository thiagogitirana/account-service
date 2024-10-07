package com.pismo.service.account.application.dto;

public record TransactionResponseDTO(
        Integer transactionId,
        Integer accountId,
        Integer operationTypeId,
        Double amount
) {
}
