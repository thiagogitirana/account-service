package com.pismo.service.account.application.dto;

public record AccountRequestDTO(
        String documentNumber
) {
    public AccountRequestDTO {

        if (documentNumber.isBlank()) {
            throw new IllegalArgumentException("Document id is required");
        }
    }
}
