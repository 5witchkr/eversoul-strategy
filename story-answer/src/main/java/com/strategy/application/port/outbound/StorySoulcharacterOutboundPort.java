package com.strategy.application.port.outbound;

import com.strategy.adapter.outbound.persistence.entity.StorySoulcharacter;

import java.util.Optional;

public interface StorySoulcharacterOutboundPort {
    StorySoulcharacter getByName(String soul);

    void save(StorySoulcharacter storySoulcharacter);

    void deleteById(Long id);

    Optional<StorySoulcharacter> findById(Long storySoulcharacterId);

    StorySoulcharacter getByReferenceId(Long soulId);
}
