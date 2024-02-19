package com.fabrick.api.fabrickaccountapi.domain;


import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Map;

@Value
@RequiredArgsConstructor
@Builder
public class TaxRelief {
    TaxReliefId taxReliefId;
    boolean isCondoUpgrade;
    String creditorFiscalCode;
    BeneficiaryType beneficiaryType;
    NaturalPersonBeneficiary naturalPersonBeneficiary;
    LegalPersonBeneficiary legalPersonBeneficiary;
}
