package com.strategy.adpater.outbound.persistence.repository;

import com.strategy.adpater.outbound.persistence.entity.TacticCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacticCharacterRepository extends JpaRepository<TacticCharacter, Long> {
}
