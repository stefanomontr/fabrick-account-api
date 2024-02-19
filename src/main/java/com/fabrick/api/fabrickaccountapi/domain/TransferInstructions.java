package com.fabrick.api.fabrickaccountapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@Value
@RequiredArgsConstructor
@Builder
public class TransferInstructions implements Serializable {
    Creditor creditor;
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate executionDate;
    String uri;
    String description;
    Number amount;
    String currency;
    Boolean isUrgent;
    Boolean isInstant;
    FeeType feeType;
    String feeAccountId;
    @JsonInclude(JsonInclude.Include.ALWAYS)
    TaxRelief taxRelief;
}
