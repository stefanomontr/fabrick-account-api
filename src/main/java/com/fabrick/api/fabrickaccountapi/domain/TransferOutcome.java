package com.fabrick.api.fabrickaccountapi.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Value
@RequiredArgsConstructor
@Builder
public class TransferOutcome {
    String moneyTransferId;
    TransferStatus status;
    TransferDirection direction;
    Creditor creditor;
    Debtor debtor;
    String cro;
    String trn;
    String uri;
    String description;
    LocalDateTime createdDatetime;
    LocalDateTime accountedDatetime;
    Date debtorValueDate;
    Date creditorValueDate;
    Amount amount;
    Boolean isUrgent;
    Boolean isInstant;
    FeeType feeType;
    String feeAccountID;
    List<Fee> fees;
    Boolean hasTaxRelief;
}
