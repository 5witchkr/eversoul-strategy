package com.strategy.application.port.outbound;

import com.strategy.adapter.outbound.persistence.SoulCharacter;

import java.util.List;
import java.util.Optional;

public interface SoulCharacterOutboundPort {

    Optional<List<SoulCharacter>> getByTier(String tier);
}
