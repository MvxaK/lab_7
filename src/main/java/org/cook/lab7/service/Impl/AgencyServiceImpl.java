package org.cook.lab7.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.cook.lab7.dto.AgencyDto;
import org.cook.lab7.entity.AgencyEntity;
import org.cook.lab7.entity.CelebrityEntity;
import org.cook.lab7.mapper.AgencyMapper;
import org.cook.lab7.repository.AgencyRepository;
import org.cook.lab7.repository.CelebrityRepository;
import org.cook.lab7.service.AgencyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {
    private final AgencyRepository agencyRepository;
    private final CelebrityRepository celebrityRepository;
    private final AgencyMapper agencyMapper;

    @Override
    @Transactional(readOnly = true)
    public AgencyDto getAgencyById(Long id) {
        AgencyEntity agencyEntity = agencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no agency with id -> " + id));

        return agencyMapper.toModel(agencyEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgencyDto> getAllAgencies() {
        List<AgencyDto> agencies = agencyRepository.findAll().stream()
                .map(agencyMapper::toModel).toList();

        return agencies;
    }

    @Override
    @Transactional
    public AgencyDto createAgency(AgencyDto agencyDto) {
        AgencyEntity agencyEntity = agencyMapper.toEntity(agencyDto);

        if(agencyDto.getCelebritiesId() != null){
            List<CelebrityEntity> celebrities = celebrityRepository.findAllById(agencyDto.getCelebritiesId());
            agencyEntity.setCelebrities(celebrities);

            for (CelebrityEntity celebrity: agencyEntity.getCelebrities()){
                celebrity.setAgency(agencyEntity);
            }
            celebrityRepository.saveAll(celebrities);
        }else
            agencyEntity.setCelebrities(new ArrayList<>());

        return agencyMapper.toModel(agencyRepository.save(agencyEntity));
    }

    @Override
    @Transactional
    public AgencyDto updateAgency(Long id, AgencyDto toUpdate) {
        AgencyEntity agencyEntity = agencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no agency with id -> " + id));

        if(toUpdate.getCelebritiesId() != null){
            List<CelebrityEntity> celebrities = celebrityRepository.findAllById(toUpdate.getCelebritiesId());
            agencyEntity.setCelebrities(celebrities);

            for (CelebrityEntity celebrity: agencyEntity.getCelebrities()){
                celebrity.setAgency(agencyEntity);
            }
            celebrityRepository.saveAll(agencyEntity.getCelebrities());
        }else
            agencyEntity.setCelebrities(new ArrayList<>());

        agencyEntity.setName(toUpdate.getName());

        return agencyMapper.toModel(agencyRepository.save(agencyEntity));
    }

    @Override
    @Transactional
    public void deleteAgency(Long id) {
        AgencyEntity agencyEntity = agencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no agency with id -> " + id));

        for (CelebrityEntity celebrity: agencyEntity.getCelebrities()){
            celebrity.setAgency(null);
        }
        celebrityRepository.saveAll(agencyEntity.getCelebrities());

        agencyRepository.delete(agencyEntity);
    }
}
