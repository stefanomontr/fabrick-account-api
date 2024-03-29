package com.fabrick.api.fabrickaccountapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApiConfig {

    @Bean
    public HttpHeaders fabrickHttpHeaders() {
        var httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Auth-Schema", "S2S");
        httpHeaders.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        httpHeaders.set("X-Time-Zone", "Europe/Rome");
        return httpHeaders;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
