package com.fabrick.api.fabrickaccountapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApiConfig {

//    @Value("${fabrick.api.key}")
//    private final String fabrickApiKey;
//
//    @Value("${fabrick.auth.schema}")
//    private final String fabrickAuthSchema;

    @Bean
    public HttpHeaders fabrickHttpHeaders() {
        var httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Auth-Schema", "S2S");
        httpHeaders.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        return httpHeaders;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
