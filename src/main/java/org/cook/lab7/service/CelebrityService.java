package org.cook.lab7.service;

import org.cook.lab7.dto.CelebrityDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CelebrityService {
    CelebrityDto createCelebrity(CelebrityDto celebrityDto);
    CelebrityDto getCelebrityById(Long id);
    List<CelebrityDto> getAllCelebrity();
    CelebrityDto updateCelebrity(Long id, CelebrityDto ToUpdate);
    void deleteCelebrity(Long id);
}
