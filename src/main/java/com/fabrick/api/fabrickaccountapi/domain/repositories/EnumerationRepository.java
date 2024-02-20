package com.fabrick.api.fabrickaccountapi.domain.repositories;

import com.fabrick.api.fabrickaccountapi.domain.entities.Enumeration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnumerationRepository extends JpaRepository<Enumeration, Enumeration.PrimaryKey> {
}
