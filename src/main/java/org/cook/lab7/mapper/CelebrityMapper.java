package org.cook.lab7.mapper;

import org.cook.lab7.dto.CelebrityDto;
import org.cook.lab7.entity.CelebrityEntity;
import org.springframework.stereotype.Component;

@Component
public class CelebrityMapper {

    public CelebrityDto toModel(CelebrityEntity entity){
        CelebrityDto dto = new CelebrityDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPseudonyms(entity.getPseudonyms());
        dto.setBirthDay(entity.getBirthDay());
        dto.setAlive(entity.isAlive());

        return dto;
    }

    public CelebrityEntity toEntity(CelebrityDto dto){
        CelebrityEntity entity = new CelebrityEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPseudonyms(dto.getPseudonyms());
        entity.setBirthDay(dto.getBirthDay());
        entity.setAlive(dto.isAlive());

        return entity;
    }
}
