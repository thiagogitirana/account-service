package com.pismo.service.account.application.controller;

import com.pismo.service.account.application.adapters.AccountAdapter;
import com.pismo.service.account.application.adapters.TransactionAdapter;
import com.pismo.service.account.application.dto.AccountRequestDTO;
import com.pismo.service.account.application.dto.AccountResponseDTO;
import com.pismo.service.account.application.dto.TransactionResponseDTO;
import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountAdapter accountAdapter;
    @Autowired
    private TransactionAdapter transactionAdapter;

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Operation(summary = "Save account", description = "Saves a new account")
    @ApiResponse(responseCode = "200", description = "success")
    @PostMapping
    public ResponseEntity<AccountResponseDTO> save(@RequestBody AccountRequestDTO accountDTO) {
        logger.info("initiating save account: {}", accountDTO);
        Account account = accountAdapter.toDomain(accountDTO);
        Account accountResponse = accountService.save(account);
        List<TransactionResponseDTO> transactions = accountResponse.getTransactions() != null? accountResponse.getTransactions().stream().map(transactionAdapter::toResponseDto).toList(): null;
        AccountResponseDTO responseDTO = accountAdapter.toResponseDTO(accountResponse, transactions);
        return ResponseEntity.ok().body(responseDTO);
    }

    @Operation(summary = "Find account by id", description = "Finds a account by a valid id")
    @ApiResponse(responseCode = "200", description = "success")
    @GetMapping(value = "/{accountId}")
    public ResponseEntity<AccountResponseDTO> findById(@PathVariable Integer accountId) {
        logger.info("initiating search for account by id");
        Account account = accountService.findById(accountId);
        List<TransactionResponseDTO> transactions = account.getTransactions() != null? account.getTransactions().stream().map(transactionAdapter::toResponseDto).toList(): null;
        AccountResponseDTO responseDTO = accountAdapter.toResponseDTO(account, transactions);
        return ResponseEntity.ok().body(responseDTO);
    }
}
