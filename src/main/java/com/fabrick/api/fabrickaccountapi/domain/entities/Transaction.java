package com.fabrick.api.fabrickaccountapi.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ENUMERATION")
    Enumeration enumeration;

    @Column(name = "ACCOUNTING_DATE")
    LocalDate accountingDate;

    @Column(name = "VALUE_DATE")
    LocalDate valueDate;

    @Column(name = "AMOUNT")
    Number amount;

    @Column(name = "CURRENCY")
    String currency;

    @Column(name = "DESCRIPTION")
    String description;
}
