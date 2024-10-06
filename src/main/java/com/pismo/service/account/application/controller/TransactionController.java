package com.pismo.service.account.application.controller;

import com.pismo.service.account.application.adapters.TransactionAdapter;
import com.pismo.service.account.application.dto.TransactionRequestDTO;
import com.pismo.service.account.application.dto.TransactionResponseDTO;
import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionAdapter transactionAdapter;
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> save(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        Transaction transaction = transactionAdapter.toDomain(transactionRequestDTO);
        Transaction transactionResponse = transactionService.create(transaction);
        TransactionResponseDTO responseDTO = transactionAdapter.toResponseDto(transactionResponse);
        return ResponseEntity.ok().body(responseDTO);
    }
}
