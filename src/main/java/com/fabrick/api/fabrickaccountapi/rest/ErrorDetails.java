package com.fabrick.api.fabrickaccountapi.rest;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@Builder
public class ErrorDetails {
    String code;
    String description;
    String params;
}
