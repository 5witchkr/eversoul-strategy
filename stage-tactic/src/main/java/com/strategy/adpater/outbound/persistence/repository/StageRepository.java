package com.strategy.adpater.outbound.persistence.repository;

import com.strategy.adpater.outbound.persistence.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StageRepository extends JpaRepository<Stage, Long> {
    Optional<Stage> getByLocationAndStep(int location, int step);
}
