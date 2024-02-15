package com.fabrick.api.fabrickaccountapi.rest;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@RequiredArgsConstructor
@Builder
public class RestResponse<T> {
    ResponseStatus status;
    List<ErrorDetails> errors;
    T payload;
}
