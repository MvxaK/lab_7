package org.cook.lab7.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.cook.lab7.dto.CelebrityDto;
import org.cook.lab7.entity.AgencyEntity;
import org.cook.lab7.entity.CelebrityEntity;
import org.cook.lab7.entity.FilmEntity;
import org.cook.lab7.mapper.CelebrityMapper;
import org.cook.lab7.repository.AgencyRepository;
import org.cook.lab7.repository.CelebrityRepository;
import org.cook.lab7.repository.FilmRepository;
import org.cook.lab7.service.CelebrityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CelebrityServiceImpl implements CelebrityService {

    private final CelebrityMapper celebrityMapper;
    private final CelebrityRepository celebrityRepository;
    private final AgencyRepository agencyRepository;
    private final FilmRepository filmRepository;

    @Override
    @Transactional(readOnly = true)
    public CelebrityDto getCelebrityById(Long id) {
        CelebrityEntity celebrityEntity = celebrityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no celebrity with id -> " + id));

        return celebrityMapper.toModel(celebrityEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CelebrityDto> getAllCelebrity() {
        List<CelebrityDto> celebrities = celebrityRepository.findAll().stream()
                .map(celebrityMapper::toModel).toList();

        return celebrities;
    }

    @Override
    @Transactional
    public CelebrityDto createCelebrity(CelebrityDto celebrityDto) {
        CelebrityEntity celebrityEntity = celebrityMapper.toEntity(celebrityDto);

        if(celebrityDto.getAgencyId() != null){
            AgencyEntity agency = agencyRepository.findById(celebrityDto.getAgencyId())
                    .orElseThrow(() -> new EntityNotFoundException("There is no agency with id -> " + celebrityDto.getAgencyId()));
            celebrityEntity.setAgency(agency);
        }else
            celebrityEntity.setAgency(null);

        if(celebrityDto.getFilmsId() != null && !celebrityDto.getFilmsId().isEmpty()){
            List<FilmEntity> films = filmRepository.findAllById(celebrityDto.getFilmsId());
            celebrityEntity.setFilms(films);

            films.forEach(film -> film.getCelebrities().add(celebrityEntity));
        }else
            celebrityEntity.setFilms(new ArrayList<>());

        return celebrityMapper.toModel(celebrityRepository.save(celebrityEntity));
    }

    @Override
    @Transactional
    public CelebrityDto updateCelebrity(Long id, CelebrityDto toUpdate) {
        CelebrityEntity celebrityEntity = celebrityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no celebrity with id -> " + id));

        if(toUpdate.getAgencyId() != null){
            AgencyEntity agency = agencyRepository.findById(toUpdate.getAgencyId())
                    .orElseThrow(() -> new EntityNotFoundException("There is no agency with id -> " + toUpdate.getAgencyId()));

            celebrityEntity.setAgency(agency);
        }else
            celebrityEntity.setAgency(null);


        List<FilmEntity> currentFilms = celebrityEntity.getFilms();
        if (currentFilms != null) {
            currentFilms.forEach(film -> film.getCelebrities().remove(celebrityEntity));

            filmRepository.saveAll(currentFilms);
        }

        if(toUpdate.getFilmsId() != null) {
            List<FilmEntity> newFilms = filmRepository.findAllById(toUpdate.getFilmsId());
            newFilms.forEach(film -> film.getCelebrities().add(celebrityEntity));

            filmRepository.saveAll(newFilms);
            celebrityEntity.setFilms(newFilms);
        }

        celebrityEntity.setName(toUpdate.getName());
        celebrityEntity.setSurname(toUpdate.getSurname());
        celebrityEntity.setPseudonyms(toUpdate.getPseudonyms());
        celebrityEntity.setBirthDay(toUpdate.getBirthDay());
        celebrityEntity.setAlive(toUpdate.isAlive());

        return celebrityMapper.toModel(celebrityRepository.save(celebrityEntity));
    }

    @Override
    @Transactional
    public void deleteCelebrity(Long id) {
        CelebrityEntity celebrityEntity = celebrityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no celebrity with id -> " + id));

        for (FilmEntity film: celebrityEntity.getFilms()){
            film.getCelebrities().remove(celebrityEntity);
        }
        filmRepository.saveAll(celebrityEntity.getFilms());
        celebrityEntity.setAgency(null);

        celebrityRepository.delete(celebrityEntity);
    }
}
