package com.fabrick.api.fabrickaccountapi.controllers;

import com.fabrick.api.fabrickaccountapi.domain.Balance;
import com.fabrick.api.fabrickaccountapi.domain.Transactions;
import com.fabrick.api.fabrickaccountapi.domain.TransferInstructions;
import com.fabrick.api.fabrickaccountapi.domain.TransferOutcome;
import com.fabrick.api.fabrickaccountapi.rest.RestResponse;
import com.fabrick.api.fabrickaccountapi.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("{accountId}")
    public RestResponse<Balance> getAccountBalance(@PathVariable("accountId") String accountId) {
        return accountService.getAccountBalance(accountId);
    }

    @PostMapping("{accountId}/transfer")
    public RestResponse<TransferOutcome> executeMoneyTransfer(
            @PathVariable("accountId") String accountId, @RequestBody TransferInstructions transferInstructions) {
        return accountService.executeMoneyTransfer(accountId, transferInstructions);
    }

    @GetMapping("{accountId}/transactions")
    public RestResponse<Transactions> getTransactions(@PathVariable("accountId") String accountId,
            @RequestParam LocalDate fromAccountingDate, @RequestParam LocalDate toAccountingDate) {
        return accountService.getTransactions(accountId, fromAccountingDate, toAccountingDate);
    }
}
