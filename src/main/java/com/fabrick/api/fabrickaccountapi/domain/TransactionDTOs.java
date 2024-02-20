package com.fabrick.api.fabrickaccountapi.domain;

import lombok.*;

import java.util.List;

@Value
@RequiredArgsConstructor
@Builder
@NoArgsConstructor(force = true)
@Setter
public class TransactionDTOs {
    List<TransactionDTO> list;
}
