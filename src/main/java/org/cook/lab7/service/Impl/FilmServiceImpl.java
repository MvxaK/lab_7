package org.cook.lab7.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.cook.lab7.dto.FilmDto;
import org.cook.lab7.entity.CelebrityEntity;
import org.cook.lab7.entity.FilmEntity;
import org.cook.lab7.mapper.FilmMapper;
import org.cook.lab7.repository.CelebrityRepository;
import org.cook.lab7.repository.FilmRepository;
import org.cook.lab7.service.FilmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final CelebrityRepository celebrityRepository;
    private final FilmMapper filmMapper;

    @Override
    @Transactional(readOnly = true)
    public FilmDto getFilmById(Long id) {
        FilmEntity filmEntity = filmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no film with id -> " + id));

        return filmMapper.toModel(filmEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmDto> getAllFilms() {
        List<FilmDto> films = filmRepository.findAll().stream()
                .map(filmMapper::toModel).toList();

        return films;
    }

    @Override
    @Transactional
    public FilmDto createFilm(FilmDto filmDto) {
        FilmEntity filmEntity = filmMapper.toEntity(filmDto);

        if (filmDto.getCelebritiesId() != null && !filmDto.getCelebritiesId().isEmpty()) {
            List<CelebrityEntity> celebrities = celebrityRepository.findAllById(filmDto.getCelebritiesId());
            filmEntity.setCelebrities(celebrities);

        }else
            filmEntity.setCelebrities(new ArrayList<>());


        return filmMapper.toModel(filmRepository.save(filmEntity));
    }

    @Override
    @Transactional
    public FilmDto updateFilm(Long id, FilmDto toUpdate) {
        FilmEntity filmEntity = filmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no film with id -> " + id));

        List<CelebrityEntity> currentCelebrities = filmEntity.getCelebrities();
        if (currentCelebrities != null) {
            currentCelebrities.forEach(celeb -> celeb.getFilms().remove(filmEntity));

            celebrityRepository.saveAll(currentCelebrities);
        }


        if(toUpdate.getCelebritiesId() != null) {
            List<CelebrityEntity> newCelebrities = celebrityRepository.findAllById(toUpdate.getCelebritiesId());
            newCelebrities.forEach(celebrity -> celebrity.getFilms().add(filmEntity));

            celebrityRepository.saveAll(newCelebrities);
            filmEntity.setCelebrities(newCelebrities);
        }

        filmEntity.setName(toUpdate.getName());
        filmEntity.setBudget(toUpdate.getBudget());

        return filmMapper.toModel(filmRepository.save(filmEntity));
    }

    @Override
    @Transactional
    public void deleteFilm(Long id) {
        if(!filmRepository.existsById(id)){
            throw new EntityNotFoundException("There is no film with id -> " + id);
        }

        filmRepository.deleteById(id);
    }
}
