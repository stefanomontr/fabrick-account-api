package com.fabrick.api.fabrickaccountapi.domain.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Entity
@Table(name = "ENUMERATION")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Value
@Builder
public class Enumeration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ENUMERATION")
    private String enumeration;

    @Column(name = "ENUMERATION_VALUE")
    private String enumerationValue;
}
