package org.cook.lab7.repository;

import org.cook.lab7.entity.CelebrityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CelebrityRepository extends JpaRepository<CelebrityEntity, Long> {

}
