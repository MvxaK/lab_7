package org.cook.lab7.controller;

import lombok.RequiredArgsConstructor;
import org.cook.lab7.dto.CelebrityDto;
import org.cook.lab7.service.Impl.CelebrityServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/celebrities")
@RequiredArgsConstructor
public class CelebrityApiController {

    private final CelebrityServiceImpl celebrityService;

    @GetMapping
    public ResponseEntity<?> getAllCelebrities(){
        List< CelebrityDto> celebrities = celebrityService.getAllCelebrity();

        return ResponseEntity.ok(celebrities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCelebrityById(@PathVariable Long id){
        CelebrityDto celebrity = celebrityService.getCelebrityById(id);

        return ResponseEntity.ok(celebrity);
    }

    @PostMapping
    public ResponseEntity<?> createCelebrity(@RequestBody CelebrityDto celebrityDto){
        CelebrityDto celebrity = celebrityService.createCelebrity(celebrityDto);

        return ResponseEntity.ok(celebrity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCelebrity(@PathVariable Long id, @RequestBody CelebrityDto celebrityDto){
        CelebrityDto celebrity = celebrityService.updateCelebrity(id, celebrityDto);

        return ResponseEntity.ok(celebrity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCelebrity(@PathVariable Long id){
        celebrityService.deleteCelebrity(id);

        return ResponseEntity.noContent().build();
    }

}
