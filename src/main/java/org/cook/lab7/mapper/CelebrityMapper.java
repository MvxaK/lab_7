package org.cook.lab7.mapper;

import org.cook.lab7.dto.CelebrityDto;
import org.cook.lab7.entity.CelebrityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CelebrityMapper {

    @Mapping(target = "agencyId", source = "agency.id")
    @Mapping(target = "filmsId", expression = "java(entity.getFilms() != null ? entity.getFilms().stream().map(film -> film.getId()).toList() : new java.util.ArrayList<>())")
    CelebrityDto toModel(CelebrityEntity entity);

    @Mapping(target = "agency", ignore = true)
    @Mapping(target = "films", ignore = true)
    CelebrityEntity toEntity(CelebrityDto dto);
}
