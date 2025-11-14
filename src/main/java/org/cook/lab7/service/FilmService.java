package org.cook.lab7.service;

import org.cook.lab7.dto.FilmDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FilmService {
    FilmDto getFilmById(Long id);
    List<FilmDto> getAllFilms();
    FilmDto createFilm(FilmDto filmDto);
    FilmDto updateFilm(Long id, FilmDto ToUpdate);
    void  deleteFilm(Long id);
}
