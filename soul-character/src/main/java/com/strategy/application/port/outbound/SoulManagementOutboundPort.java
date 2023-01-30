package com.strategy.application.port.outbound;

import com.strategy.adapter.outbound.persistence.SoulCharacter;

import java.util.Optional;

public interface SoulManagementOutboundPort {
    SoulCharacter save(SoulCharacter soulCharacter);

    Optional<SoulCharacter> findById(Long id);

    void deleteById(Long id);
}
