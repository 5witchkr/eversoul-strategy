package com.strategy.adapter.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SoulCharacterRepository extends JpaRepository<SoulCharacter, Long> {

    Optional<List<SoulCharacter>> getByTier(String tier);
}
