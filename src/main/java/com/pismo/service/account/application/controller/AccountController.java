package com.pismo.service.account.application.controller;

import com.pismo.service.account.application.adapters.AccountAdapter;
import com.pismo.service.account.application.dto.AccountRequestDTO;
import com.pismo.service.account.application.dto.AccountResponseDTO;
import com.pismo.service.account.domain.entities.Account;
import com.pismo.service.account.domain.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountAdapter accountAdapter;

    @Operation(summary = "Save account", description = "Saves a new account")
    @ApiResponse(responseCode = "200", description = "success")
    @PostMapping
    public ResponseEntity<AccountResponseDTO> save(@RequestBody AccountRequestDTO accountDTO) {
        Account account = accountAdapter.toDomain(accountDTO);
        Account accountResponse = accountService.save(account);
        AccountResponseDTO responseDTO = accountAdapter.toResponseDTO(accountResponse);
        return ResponseEntity.ok().body(responseDTO);
    }

    @Operation(summary = "Find account by id", description = "Finds a account by a valid id")
    @ApiResponse(responseCode = "200", description = "success")
    @GetMapping(value = "/{accountId}")
    public ResponseEntity<AccountResponseDTO> findById(@PathVariable Integer accountId) {
        Account account = accountService.findById(accountId);
        AccountResponseDTO responseDTO = accountAdapter.toResponseDTO(account);
        return ResponseEntity.ok().body(responseDTO);
    }
}
