package com.fabrick.api.fabrickaccountapi.domain;

import lombok.*;

@Getter
@AllArgsConstructor
public enum TaxReliefId {
    ID_119R("119R"), DL50("DL50"), L296("L296"), L449("L449"), L234("L234");

    private final String code;
}
