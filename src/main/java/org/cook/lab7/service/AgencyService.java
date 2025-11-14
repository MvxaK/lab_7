package org.cook.lab7.service;

import org.cook.lab7.dto.AgencyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgencyService {
    AgencyDto getAgencyById(Long id);
    List<AgencyDto> getAllAgencies();
    AgencyDto createAgency(AgencyDto agencyDto);
    AgencyDto updateAgency(Long id, AgencyDto ToUpdate);
    void deleteAgency(Long id);
}
