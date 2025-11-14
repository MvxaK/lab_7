package org.cook.lab7.mapper;

import org.cook.lab7.dto.AgencyDto;
import org.cook.lab7.entity.AgencyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgencyMapper {
    @Mapping(target = "celebritiesId", expression = "java(entity.getCelebrities() != null ? entity.getCelebrities().stream().map(celebrity -> celebrity.getId()).toList() : new java.util.ArrayList<>())")
    AgencyDto toModel(AgencyEntity entity);

    @Mapping(target = "celebrities", ignore = true)
    AgencyEntity toEntity(AgencyDto dto);
}
