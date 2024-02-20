package com.fabrick.api.fabrickaccountapi.services;

import com.fabrick.api.fabrickaccountapi.config.TestConfig;
import com.fabrick.api.fabrickaccountapi.domain.TransactionDTO;
import com.fabrick.api.fabrickaccountapi.domain.entities.Transaction;
import com.fabrick.api.fabrickaccountapi.domain.repositories.TransactionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

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
        var trans = TransactionDTO.builder().transactionId("123").operationId("321").build();
        transactionService.saveTransactions(Collections.singletonList(trans));
        Assertions.assertThat(transactionRepository.findAll()).hasSize(1)
                .element(0)
                .returns(trans.getTransactionId(), Transaction::getTransactionId)
                .returns(trans.getOperationId(), Transaction::getOperationId);
    }
}