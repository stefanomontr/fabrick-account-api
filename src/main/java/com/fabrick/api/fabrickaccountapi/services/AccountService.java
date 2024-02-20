package com.fabrick.api.fabrickaccountapi.services;

import com.fabrick.api.fabrickaccountapi.domain.*;
import com.fabrick.api.fabrickaccountapi.domain.entities.Transaction;
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
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final RestTemplate restTemplate;
    private final HttpHeaders fabrickHttpHeaders;
    private final TransactionService transactionService;

    private static final String BASE_URL = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final ParameterizedTypeReference<RestResponse<Balance>> balanceResponseType
            = new ParameterizedTypeReference<RestResponse<Balance>>() {};

    private static final ParameterizedTypeReference<RestResponse<TransferOutcome>> transferResponseType
            = new ParameterizedTypeReference<RestResponse<TransferOutcome>>() {};
    private static final ParameterizedTypeReference<RestResponse<TransactionDTOs>> transactionListResponseType
            = new ParameterizedTypeReference<RestResponse<TransactionDTOs>>() {};

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

    public RestResponse<TransactionDTOs> getTransactions(
            String accountId, LocalDate fromAccountingDate, LocalDate toAccountingDate) {
        var url = BASE_URL.replace("{accountId}", accountId).concat("/transactions?")
                .concat("fromAccountingDate=").concat(fromAccountingDate.format(DATE_FORMATTER))
                .concat("&toAccountingDate=").concat(toAccountingDate.format(DATE_FORMATTER));
        var entity = new HttpEntity<RestResponse<TransactionDTOs>>(fabrickHttpHeaders);
        ResponseEntity<RestResponse<TransactionDTOs>> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, transactionListResponseType);
            if (Objects.nonNull(response.getBody()) && Objects.nonNull(response.getBody().getPayload())) {
                transactionService.saveTransactions(response.getBody().getPayload().getList());
            }
            return response.getBody();
        } catch (RestClientResponseException e) {
            return ExceptionUtils.restClientResponseError(e);
        }
    }
}
