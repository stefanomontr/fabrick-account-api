package com.fabrick.api.fabrickaccountapi.services;

import com.fabrick.api.fabrickaccountapi.domain.Balance;
import com.fabrick.api.fabrickaccountapi.domain.Transactions;
import com.fabrick.api.fabrickaccountapi.domain.TransferInstructions;
import com.fabrick.api.fabrickaccountapi.domain.TransferOutcome;
import com.fabrick.api.fabrickaccountapi.rest.RestResponse;
import com.fabrick.api.fabrickaccountapi.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final RestTemplate restTemplate;
    private final HttpHeaders fabrickHttpHeaders;

    private static final String BASE_URL = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final ParameterizedTypeReference<RestResponse<Balance>> balanceResponseType
            = new ParameterizedTypeReference<RestResponse<Balance>>() {};

    private static final ParameterizedTypeReference<RestResponse<TransferOutcome>> transferResponseType
            = new ParameterizedTypeReference<RestResponse<TransferOutcome>>() {};
    private static final ParameterizedTypeReference<RestResponse<Transactions>> transactionListResponseType
            = new ParameterizedTypeReference<RestResponse<Transactions>>() {};

    public RestResponse<Balance> getAccountBalance(String accountId) {
        var url = BASE_URL.replace("{accountId}", accountId).concat("/balance");
        var entity = new HttpEntity<RestResponse<Balance>>(fabrickHttpHeaders);
        ResponseEntity<RestResponse<Balance>> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, balanceResponseType);
        } catch (RestClientResponseException e) {
            return ExceptionUtils.restClientResponseError(e);
        }
        return response.getBody();
    }

    public RestResponse<TransferOutcome> executeMoneyTransfer(String accountId, TransferInstructions transferInstructions) {
        var url = BASE_URL.replace("{accountId}", accountId).concat("/payments/money-transfers");
        var entity = new HttpEntity<>(transferInstructions, fabrickHttpHeaders);
        ResponseEntity<RestResponse<TransferOutcome>> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, entity, transferResponseType);
        } catch (RestClientResponseException e) {
            return ExceptionUtils.restClientResponseError(e);
        }
        return response.getBody();
    }

    public RestResponse<Transactions> getTransactions(
            String accountId, LocalDate fromAccountingDate, LocalDate toAccountingDate) {
        var url = BASE_URL.replace("{accountId}", accountId).concat("/transactions?")
                .concat("fromAccountingDate=").concat(fromAccountingDate.format(DATE_FORMATTER))
                .concat("&toAccountingDate=").concat(toAccountingDate.format(DATE_FORMATTER));
        var entity = new HttpEntity<RestResponse<Transactions>>(fabrickHttpHeaders);
        ResponseEntity<RestResponse<Transactions>> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, transactionListResponseType);
        } catch (RestClientResponseException e) {
            return ExceptionUtils.restClientResponseError(e);
        }
        return response.getBody();
    }
}
