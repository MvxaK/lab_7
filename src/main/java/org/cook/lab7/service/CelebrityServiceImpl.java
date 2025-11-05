package org.cook.lab7.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.cook.lab7.dto.CelebrityDto;
import org.cook.lab7.entity.CelebrityEntity;
import org.cook.lab7.mapper.CelebrityMapper;
import org.cook.lab7.repository.CelebrityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CelebrityServiceImpl implements CelebrityService{

    private final CelebrityMapper celebrityMapper;
    private final CelebrityRepository celebrityRepository;

    @Override
    public CelebrityDto createCelebrity(CelebrityDto celebrityDto) {
        CelebrityEntity celebrityEntity = celebrityMapper.toEntity(celebrityDto);

        return celebrityMapper.toModel(celebrityRepository.save(celebrityEntity));
    }

    @Override
    public CelebrityDto getCelebrityById(Long id) {
        CelebrityEntity celebrityEntity = celebrityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no celebrity with id -> " + id));

        return celebrityMapper.toModel(celebrityEntity);
    }

    @Override
    public List<CelebrityDto> getAllCelebrity() {
        List<CelebrityDto> celebrities = celebrityRepository.findAll().stream()
                .map(celebrityMapper::toModel).toList();

        return celebrities;
    }

    @Override
    public CelebrityDto updateCelebrity(Long id, CelebrityDto toUpdate) {
        CelebrityEntity entity = celebrityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no celebrity with id -> " + id));

        entity.setName(toUpdate.getName());
        entity.setSurname(toUpdate.getSurname());
        entity.setPseudonyms(toUpdate.getPseudonyms());
        entity.setBirthDay(toUpdate.getBirthDay());
        entity.setAlive(toUpdate.isAlive());


        return celebrityMapper.toModel(celebrityRepository.save(entity));
    }

    @Override
    public void deleteCelebrity(Long id) {
        if(!celebrityRepository.existsById(id)){
            throw new EntityNotFoundException("There is no celebrity with id -> " + id);
        }

        celebrityRepository.deleteById(id);
    }
}
