package org.cook.lab7.controller;

import lombok.RequiredArgsConstructor;
import org.cook.lab7.dto.FilmDto;
import org.cook.lab7.service.Impl.FilmServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
@RequiredArgsConstructor
public class FilmApiController {

    private final FilmServiceImpl filmService;

    @GetMapping
    public ResponseEntity<?> getAllFilms(){
        List<FilmDto> films = filmService.getAllFilms();

        return ResponseEntity.ok(films);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFilmById(@PathVariable Long id){
        FilmDto film = filmService.getFilmById(id);

        return ResponseEntity.ok(film);
    }

    @PostMapping
    public ResponseEntity<?> createFilm(@RequestBody FilmDto filmDto){
        FilmDto film = filmService.createFilm(filmDto);

        return ResponseEntity.ok(film);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFilm(@PathVariable Long id, @RequestBody FilmDto filmDto){
        FilmDto film = filmService.updateFilm(id, filmDto);

        return ResponseEntity.ok(film);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable Long id){
        filmService.deleteFilm(id);

        return ResponseEntity.noContent().build();
    }

}
