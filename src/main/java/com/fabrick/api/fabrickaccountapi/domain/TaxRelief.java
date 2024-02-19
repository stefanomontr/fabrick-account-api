package com.fabrick.api.fabrickaccountapi.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@RequiredArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.ALWAYS)
public class TaxRelief implements Serializable {
    String taxReliefId;
    Boolean isCondoUpgrade;
    String creditorFiscalCode;
    String beneficiaryType;
    NaturalPersonBeneficiary naturalPersonBeneficiary;
    LegalPersonBeneficiary legalPersonBeneficiary;
}
