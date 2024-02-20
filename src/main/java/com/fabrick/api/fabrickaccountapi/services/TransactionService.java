package com.fabrick.api.fabrickaccountapi.services;

import com.fabrick.api.fabrickaccountapi.domain.TransactionDTO;
import com.fabrick.api.fabrickaccountapi.domain.entities.Transaction;
import com.fabrick.api.fabrickaccountapi.domain.repositories.TransactionRepository;
import com.fabrick.api.fabrickaccountapi.mappers.TransactionMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    private Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Transactional
    public void saveTransactions(List<TransactionDTO> transactionDTOs) {
        if (Objects.nonNull(transactionDTOs)) {
            var transactions = transactionDTOs.stream().map(transactionMapper::toTransaction).toList();
            transactions.forEach(this::saveTransaction);
        }
    }
}
