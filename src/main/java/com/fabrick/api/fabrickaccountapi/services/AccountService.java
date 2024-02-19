package com.fabrick.api.fabrickaccountapi.services;

import com.fabrick.api.fabrickaccountapi.domain.Balance;
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

@Service
@RequiredArgsConstructor
public class AccountService {

    private final RestTemplate restTemplate;
    private final HttpHeaders fabrickHttpHeaders;

//    TODO
//    @Value("${fabrick.api.url}")
//    private final String baseUrl;
//    @Value("${fabrick.balance.endpoint}")
//    private final String balanceEndpoint;

    private static final String BASE_URL = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}";

    private static final ParameterizedTypeReference<RestResponse<Balance>> balanceResponseType
            = new ParameterizedTypeReference<RestResponse<Balance>>() {};

    private static final ParameterizedTypeReference<RestResponse<TransferOutcome>> transferResponseType
            = new ParameterizedTypeReference<RestResponse<TransferOutcome>>() {};

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
        fabrickHttpHeaders.set("X-Time-Zone", "Europe/Rome");
        var entity = new HttpEntity<>(transferInstructions, fabrickHttpHeaders);
        ResponseEntity<RestResponse<TransferOutcome>> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, entity, transferResponseType);
        } catch (RestClientResponseException e) {
            return ExceptionUtils.restClientResponseError(e);
        }
        return response.getBody();
    }
}
