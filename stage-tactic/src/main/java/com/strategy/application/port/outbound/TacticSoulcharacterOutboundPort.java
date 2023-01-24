package com.strategy.application.port.outbound;

import com.strategy.adpater.outbound.persistence.entity.TacticSoulcharacter;

import java.util.Optional;

public interface TacticSoulcharacterOutboundPort {
    Optional<TacticSoulcharacter> getByName(String name);

    void save(TacticSoulcharacter tacticSoulcharacter);

    void deleteById(Long id);

    Optional<TacticSoulcharacter> findById(Long id);
}
