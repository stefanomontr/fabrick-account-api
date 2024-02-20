package com.fabrick.api.fabrickaccountapi.mappers;

import com.fabrick.api.fabrickaccountapi.domain.EnumerationDTO;
import com.fabrick.api.fabrickaccountapi.domain.entities.Enumeration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EnumerationMapper {

    @Mapping(target = "primaryKey.enumeration", source = "enumeration")
    @Mapping(target = "primaryKey.enumerationValue", source = "enumerationValue")
    Enumeration toEnumeration(EnumerationDTO enumerationDTO);
}
