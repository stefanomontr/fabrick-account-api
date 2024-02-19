package com.fabrick.api.fabrickaccountapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value
@RequiredArgsConstructor
@Builder
public class TransferInstructions {
    Creditor creditor;
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate executionDate;
    String uri;
    String description;
    String amount;
    String currency;
    Boolean isUrgent;
    Boolean isInstant;
    FeeType feeType;
    String feeAccountId;
    TaxRelief taxRelief;
}
