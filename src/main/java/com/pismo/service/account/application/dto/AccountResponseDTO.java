package com.pismo.service.account.application.dto;

import java.util.List;

public record AccountResponseDTO(
        Integer accountId,
        String documentNumber,
        List<TransactionResponseDTO> transactions
) {
}
