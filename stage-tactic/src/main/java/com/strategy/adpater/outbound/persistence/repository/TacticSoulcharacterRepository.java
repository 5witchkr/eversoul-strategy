package com.strategy.adpater.outbound.persistence.repository;

import com.strategy.adpater.outbound.persistence.entity.TacticSoulcharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TacticSoulcharacterRepository extends JpaRepository<TacticSoulcharacter, Long> {
    Optional<TacticSoulcharacter> getByName(String name);
}
