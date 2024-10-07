package com.pismo.service.account.application.dto;

public record TransactionRequestDTO(
        Integer accountId,
        Integer operationTypeId,
        Double amount
) {
    public TransactionRequestDTO {
        if (accountId == null) {
            throw new IllegalArgumentException("account id is required");
        }
        if (operationTypeId == null) {
            throw new IllegalArgumentException("operation type id is required");
        }
        if (amount == null) {
            throw new IllegalArgumentException("amount is required");
        }
    }
}
