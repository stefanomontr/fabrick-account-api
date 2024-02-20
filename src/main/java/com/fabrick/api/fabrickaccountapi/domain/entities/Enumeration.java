package com.fabrick.api.fabrickaccountapi.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "ENUMERATION")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Value
@Builder
public class Enumeration {

    @EmbeddedId
    private PrimaryKey primaryKey;

    @OneToMany(mappedBy = "enumeration")
    private List<Transaction> transactions;

    @Embeddable
    @Data
    public static class PrimaryKey {

        @Column(name = "ENUMERATION")
        private String enumeration;

        @Column(name = "ENUMERATION_VALUE")
        private String enumerationValue;
    }
}
