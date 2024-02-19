package com.fabrick.api.fabrickaccountapi.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value
@RequiredArgsConstructor
@Builder
public class Transaction {
    String transactionId;
    String operationId;
    LocalDate accountingDate;
    LocalDate valueDate;
    Enumeration enumeration;
    Number amount;
    String currency;
    String description;
}
