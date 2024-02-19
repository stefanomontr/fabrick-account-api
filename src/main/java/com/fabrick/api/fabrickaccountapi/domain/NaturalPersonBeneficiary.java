package com.fabrick.api.fabrickaccountapi.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@Builder
public class NaturalPersonBeneficiary {
    String fiscalCode1;
    String fiscalCode2;
    String fiscalCode3;
    String fiscalCode4;
    String fiscalCode5;
}
