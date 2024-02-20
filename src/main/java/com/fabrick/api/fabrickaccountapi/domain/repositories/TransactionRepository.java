package com.fabrick.api.fabrickaccountapi.domain.repositories;

import com.fabrick.api.fabrickaccountapi.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
