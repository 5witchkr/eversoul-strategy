package com.strategy.application.port.outbound;

import com.strategy.adpater.outbound.persistence.entity.TacticCharacter;

import java.util.List;

public interface TacticCharacterOutboundPort {

    TacticCharacter save(TacticCharacter tacticCharacter);

    List<TacticCharacter> saveAll(List<TacticCharacter> tacticCharacters);

    Long countAll();
}
