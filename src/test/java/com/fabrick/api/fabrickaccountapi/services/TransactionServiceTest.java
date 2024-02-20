package com.fabrick.api.fabrickaccountapi.services;

import com.fabrick.api.fabrickaccountapi.config.TestConfig;
import com.fabrick.api.fabrickaccountapi.domain.EnumerationDTO;
import com.fabrick.api.fabrickaccountapi.domain.TransactionDTO;
import com.fabrick.api.fabrickaccountapi.domain.entities.Transaction;
import com.fabrick.api.fabrickaccountapi.domain.repositories.TransactionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.Collections;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
class TransactionServiceTest {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionService transactionService;

    @Test
    void testSaveTransactions() {
        Assertions.assertThat(transactionRepository.findAll()).isEmpty();
        var enumeration = EnumerationDTO.builder()
                .enumeration("enumeration")
                .enumerationValue("value")
                .build();
        var transaction = TransactionDTO.builder()
                .transactionId("123")
                .operationId("321")
                .accountingDate(LocalDate.of(2019, 1, 1))
                .valueDate(LocalDate.of(2019, 2, 2))
                .currency("EUR")
                .description("description")
                .enumeration(enumeration)
                .build();

        transactionService.saveTransactions(Collections.singletonList(transaction));

        Assertions.assertThat(transactionRepository.findAll()).hasSize(1)
                .element(0)
                .returns(transaction.getTransactionId(), Transaction::getTransactionId)
                .returns(transaction.getOperationId(), Transaction::getOperationId)
                .returns(transaction.getAccountingDate(), Transaction::getAccountingDate)
                .returns(transaction.getValueDate(), Transaction::getValueDate)
                .returns(transaction.getCurrency(), Transaction::getCurrency)
                .returns(transaction.getDescription(), Transaction::getDescription)
                .returns(enumeration.getEnumeration(), tx -> tx.getEnumeration().getEnumeration())
                .returns(enumeration.getEnumerationValue(), tx -> tx.getEnumeration().getEnumerationValue());
    }
}