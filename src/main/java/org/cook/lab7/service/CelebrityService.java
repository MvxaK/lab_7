package org.cook.lab7.service;

import org.cook.lab7.dto.CelebrityDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CelebrityService {
    CelebrityDto getCelebrityById(Long id);
    List<CelebrityDto> getAllCelebrity();
    CelebrityDto createCelebrity(CelebrityDto celebrityDto);
    CelebrityDto updateCelebrity(Long id, CelebrityDto ToUpdate);
    void deleteCelebrity(Long id);
}
