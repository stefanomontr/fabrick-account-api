package com.fabrick.api.fabrickaccountapi.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@Builder
public class EnumerationDTO {
    String enumeration;
    String enumerationValue;
}
