package com.fabrick.api.fabrickaccountapi.controllers;

import com.fabrick.api.fabrickaccountapi.domain.Balance;
import com.fabrick.api.fabrickaccountapi.rest.RestResponse;
import com.fabrick.api.fabrickaccountapi.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("{accountId}")
    public RestResponse<Balance> getAccountBalance(@PathVariable("accountId") String accountId) {
        return accountService.getAccountBalance(accountId);
    }
}
