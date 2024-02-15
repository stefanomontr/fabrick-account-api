package com.fabrick.api.fabrickaccountapi.services;

import com.fabrick.api.fabrickaccountapi.domain.Balance;
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

    private static final ParameterizedTypeReference<RestResponse<Balance>> balanceResponseType
            = new ParameterizedTypeReference<RestResponse<Balance>>() {};

    public RestResponse<Balance> getAccountBalance(String accountId) {
        var url = "https://sandbox.platfr.io/".concat("/api/gbs/banking/v4.0/accounts/{accountId}/balance".replace("{accountId}", accountId));
        var entity = new HttpEntity<RestResponse<Balance>>(fabrickHttpHeaders);
        ResponseEntity<RestResponse<Balance>> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, balanceResponseType);
        } catch (RestClientResponseException e) {
            return ExceptionUtils.restClientResponseError(e);
        }
        return response.getBody();
    }
}
