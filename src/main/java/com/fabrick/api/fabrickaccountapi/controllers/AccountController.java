package com.fabrick.api.fabrickaccountapi.controllers;

import com.fabrick.api.fabrickaccountapi.domain.*;
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
}
