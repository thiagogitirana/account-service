package com.pismo.service.account.application.dto;

public record AccountRequestDTO(
        String documentNumber,
        Double limit
) {
    public AccountRequestDTO {

        if (documentNumber.isBlank()) {
            throw new IllegalArgumentException("Document id is required");
        }

        if (limit == null) {
            throw new IllegalArgumentException("Limit id is required");
        }
    }
}
