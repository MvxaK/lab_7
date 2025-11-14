package org.cook.lab7.mapper;

import org.cook.lab7.dto.FilmDto;
import org.cook.lab7.entity.FilmEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FilmMapper {

    @Mapping(target = "celebritiesId", expression = "java(entity.getCelebrities() != null ? entity.getCelebrities().stream().map(celebrity -> celebrity.getId()).toList() : new java.util.ArrayList<>())")
    FilmDto toModel(FilmEntity entity);

    @Mapping(target = "celebrities", ignore = true)
    FilmEntity toEntity(FilmDto dto);
}