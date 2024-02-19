package com.fabrick.api.fabrickaccountapi.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@RequiredArgsConstructor
@Builder
public class LegalPersonBeneficiary implements Serializable {
    String fiscalCode;
    String legalRepresentativeFiscalCode;
}
