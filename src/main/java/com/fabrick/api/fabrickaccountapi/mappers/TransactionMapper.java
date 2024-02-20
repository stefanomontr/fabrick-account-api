package com.fabrick.api.fabrickaccountapi.mappers;

import com.fabrick.api.fabrickaccountapi.domain.EnumerationDTO;
import com.fabrick.api.fabrickaccountapi.domain.TransactionDTO;
import com.fabrick.api.fabrickaccountapi.domain.entities.Enumeration;
import com.fabrick.api.fabrickaccountapi.domain.entities.Transaction;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
@NoArgsConstructor
public abstract class TransactionMapper {

    @Autowired
    private EnumerationMapper enumerationMapper;

    @Mapping(target = "enumeration", source = "enumeration", qualifiedByName = "toEnumeration")
    public abstract Transaction toTransaction(TransactionDTO transactionDTO);

    @Named("toEnumeration")
    public Enumeration toEnumeration(EnumerationDTO enumeration) {
        return enumerationMapper.toEnumeration(enumeration);
    }
}
