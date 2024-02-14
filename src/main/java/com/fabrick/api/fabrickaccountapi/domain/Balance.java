package com.fabrick.api.fabrickaccountapi.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Date;

@Value
@RequiredArgsConstructor
@Builder
public class Balance {
    Date date;
    BigDecimal balance;
    BigDecimal availableBalance;
    String currency;
}
