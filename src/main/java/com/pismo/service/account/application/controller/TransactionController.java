package com.pismo.service.account.application.controller;

import com.pismo.service.account.application.adapters.TransactionAdapter;
import com.pismo.service.account.application.dto.TransactionRequestDTO;
import com.pismo.service.account.application.dto.TransactionResponseDTO;
import com.pismo.service.account.domain.entities.Transaction;
import com.pismo.service.account.domain.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionAdapter transactionAdapter;
    @Autowired
    private TransactionService transactionService;

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Operation(summary = "Create transaction", description = "Creates a new transaction")
    @ApiResponse(responseCode = "200", description = "success")
    @PostMapping
    public ResponseEntity<TransactionResponseDTO> save(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        logger.info("initiating create transaction: {}", transactionRequestDTO);
        Transaction transaction = transactionAdapter.toDomain(transactionRequestDTO);
        Transaction transactionResponse = transactionService.create(transaction);
        TransactionResponseDTO responseDTO = transactionAdapter.toResponseDto(transactionResponse);
        return ResponseEntity.ok().body(responseDTO);
    }

    @Operation(summary = "Get Balance", description = "gets account balance")
    @ApiResponse(responseCode = "200", description = "success")
    @GetMapping(value = "/{accountId}")
    public ResponseEntity<Double> balance(@PathVariable Integer accountId){
        logger.info("initiating geting balance for account: {}", accountId);
        return ResponseEntity.ok().body(transactionService.balance(accountId));
    }
}
