package com.fabrick.api.fabrickaccountapi.services;

import com.fabrick.api.fabrickaccountapi.config.TestConfig;
import com.fabrick.api.fabrickaccountapi.domain.*;
import com.fabrick.api.fabrickaccountapi.domain.entities.Transaction;
import com.fabrick.api.fabrickaccountapi.domain.repositories.TransactionRepository;
import com.fabrick.api.fabrickaccountapi.rest.ErrorDetails;
import com.fabrick.api.fabrickaccountapi.rest.ResponseStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionRepository transactionRepository;

    private static final String TEST_ACCOUNT = "14537780";

    @Test
    void testMissingAccount() {
        var missingAccount = "000";
        var errorResponse = accountService.getAccountBalance(missingAccount);
        Assertions.assertThat(errorResponse.getPayload()).isNull();
        Assertions.assertThat(errorResponse.getStatus()).isEqualTo(ResponseStatus.KO);
        Assertions.assertThat(errorResponse.getErrors())
                .hasSize(1)
                .element(0)
                .returns("REQ004", ErrorDetails::getCode)
                .returns("Invalid account identifier", ErrorDetails::getDescription);
    }

    @Test
    void testGetBalance() {
        var todayAtStartOfDay = Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC));
        var response = accountService.getAccountBalance(TEST_ACCOUNT);
        Assertions.assertThat(response.getPayload()).isNotNull()
                .returns(0, payload -> todayAtStartOfDay.compareTo(payload.getDate()));
        Assertions.assertThat(response.getPayload().getBalance()).isNotNull();
        Assertions.assertThat(response.getPayload().getAvailableBalance()).isNotNull();
        Assertions.assertThat(response.getPayload().getCurrency()).isNotNull();
    }

    @Test
    void testMoneyTransfer() {
        var transferInstructions = TransferInstructions.builder()
                .creditor(Creditor.builder()
                        .name("John Doe")
                        .account(Account.builder()
                                .accountCode("IT23A0336844430152923804660")
                                .bicCode("SELBIT2BXXX")
                                .build())
                        .address(Address.builder()
                                .address(null)
                                .city(null)
                                .countryCode(null)
                                .build())
                        .build())
                .executionDate(LocalDate.of(2019, 4, 1))
                .uri("REMITTANCE_INFORMATION")
                .description("Payment invoice 75/2017")
                .amount(800)
                .currency("EUR")
                .isUrgent(false)
                .isInstant(false)
                .feeType(FeeType.SHA)
                .feeAccountId("45685475")
                .taxRelief(TaxRelief.builder()
                        .taxReliefId("L449")
                        .isCondoUpgrade(false)
                        .creditorFiscalCode("56258745832")
                        .beneficiaryType("NATURAL_PERSON")
                        .naturalPersonBeneficiary(NaturalPersonBeneficiary.builder()
                                .fiscalCode1("MRLFNC81L04A859L")
                                .build())
                        .legalPersonBeneficiary(LegalPersonBeneficiary.builder()
                                .fiscalCode(null)
                                .legalRepresentativeFiscalCode(null)
                                .build())
                        .build())
                .build();
        var response = accountService.executeMoneyTransfer(TEST_ACCOUNT, transferInstructions);
        Assertions.assertThat(response.getErrors())
                .hasSize(1)
                .element(0)
                .returns("API000", ErrorDetails::getCode)
                .returns("it.sella.pagamenti.servizibonifico.exception.ServiziInvioBonificoSubsystemException: " +
                                "it.sella.pagamenti.sottosistemi.SottosistemiException: " +
                                "Errore tecnico CONTO 45685475:Conto 45685475 non esiste",
                        ErrorDetails::getDescription);
    }

    @Test
    void testGetTransactions() {
        Assertions.assertThat(transactionRepository.findAll()).isEmpty();

        var from = LocalDate.of(2019, 1, 1);
        var to = LocalDate.of(2019, 12, 1);
        var response = accountService.getTransactions(TEST_ACCOUNT, from, to);
        // test response from Fabrick api
        Assertions.assertThat(response.getPayload()).isNotNull();
        Assertions.assertThat(response.getPayload().getList()).hasSize(14);

        // test transactions saved in db
        var savedTransactions = transactionRepository.findAll();
        Assertions.assertThat(savedTransactions).hasSize(14);
    }
}