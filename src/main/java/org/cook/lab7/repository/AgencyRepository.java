package org.cook.lab7.repository;

import org.cook.lab7.entity.AgencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyRepository extends JpaRepository<AgencyEntity, Long> {
}
