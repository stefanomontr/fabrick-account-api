package com.fabrick.api.fabrickaccountapi.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value
@RequiredArgsConstructor
@Builder
public class TransactionDTO {
    String transactionId;
    String operationId;
    LocalDate accountingDate;
    LocalDate valueDate;
    EnumerationDTO enumeration;
    Number amount;
    String currency;
    String description;
}
