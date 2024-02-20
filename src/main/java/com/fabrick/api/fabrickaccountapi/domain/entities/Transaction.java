package com.fabrick.api.fabrickaccountapi.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "TRANSACTIONS")
@Value
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Setter
public class Transaction {

    @Id
    @Column(name = "TRANSACTION_ID")
    String transactionId;

    @Column(name = "OPERATION_ID")
    String operationId;
}
