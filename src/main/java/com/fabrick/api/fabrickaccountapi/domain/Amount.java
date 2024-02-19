package com.fabrick.api.fabrickaccountapi.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Date;

@Value
@RequiredArgsConstructor
@Builder
public class Amount {
    Number debtorAmount;
    String debtorCurrency;
    Number creditorAmount;
    String creditorCurrency;
    Date creditorCurrencyDate;
    Number exchangeRate;
}
