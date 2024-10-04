package com.pismo.service.account.application.dto;

public record TransactionRequestDTO(
        Integer accountId,
        Integer operationTypeId,
        Double amount
) {
    public TransactionRequestDTO {
        if (accountId == null) {
            throw new IllegalArgumentException("a account id is required");
        }
        if (operationTypeId == null) {
            throw new IllegalArgumentException("a account id is required");
        }
    }
}
