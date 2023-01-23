package com.strategy.adpater.outbound.persistence.repository;

import com.strategy.adpater.outbound.persistence.entity.Tactic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacticRepository extends JpaRepository<Tactic, Long> {
}
