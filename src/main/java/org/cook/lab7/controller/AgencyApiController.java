package org.cook.lab7.controller;

import lombok.RequiredArgsConstructor;
import org.cook.lab7.dto.AgencyDto;
import org.cook.lab7.service.Impl.AgencyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencies")
@RequiredArgsConstructor
public class AgencyApiController {

    private final AgencyServiceImpl agencyService;

    @GetMapping
    public ResponseEntity<?> getAllAgencies(){
        List<AgencyDto> agencies = agencyService.getAllAgencies();

        return ResponseEntity.ok(agencies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAgencyById(@PathVariable Long id){
        AgencyDto agency = agencyService.getAgencyById(id);

        return ResponseEntity.ok(agency);
    }

    @PostMapping
    public ResponseEntity<?> createAgency(@RequestBody AgencyDto agencyDto){
        AgencyDto agency = agencyService.createAgency(agencyDto);

        return ResponseEntity.ok(agency);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> getAllAgencies(@PathVariable Long id, @RequestBody AgencyDto agencyDto){
        AgencyDto agency = agencyService.updateAgency(id, agencyDto);

        return ResponseEntity.ok(agency);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAgency(@PathVariable Long id){
        agencyService.deleteAgency(id);

        return ResponseEntity.noContent().build();
    }
}
