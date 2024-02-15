package com.fabrick.api.fabrickaccountapi.services;

import com.fabrick.api.fabrickaccountapi.config.TestConfig;
import com.fabrick.api.fabrickaccountapi.rest.ErrorDetails;
import com.fabrick.api.fabrickaccountapi.rest.ResponseStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    void testMissingAccount() {
        var missingAccount = "000";
        var errorResponse = accountService.getAccountBalance(missingAccount);
        Assertions.assertThat(errorResponse.getPayload()).isNull();
        Assertions.assertThat(errorResponse.getStatus()).isEqualTo(ResponseStatus.KO);
        Assertions.assertThat(errorResponse.getErrors())
                .hasSize(1)
                .element(0)
                .returns("REQ004", ErrorDetails::getCode)
                .returns("Invalid account identifier", ErrorDetails::getDescription);
    }

    @Test
    void testGetBalance() {
        var account = "14537780";
        var todayAtStartOfDay = Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC));
        var response = accountService.getAccountBalance(account);
        Assertions.assertThat(response.getPayload()).isNotNull()
                .returns(0, payload -> todayAtStartOfDay.compareTo(payload.getDate()));
        Assertions.assertThat(response.getPayload().getBalance()).isNotNull();
        Assertions.assertThat(response.getPayload().getAvailableBalance()).isNotNull();
        Assertions.assertThat(response.getPayload().getCurrency()).isNotNull();
    }
}