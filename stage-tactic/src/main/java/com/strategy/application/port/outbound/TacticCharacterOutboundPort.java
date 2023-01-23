package com.strategy.application.port.outbound;

import com.strategy.adpater.outbound.persistence.entity.TacticCharacter;

public interface TacticCharacterOutboundPort {

    TacticCharacter save(TacticCharacter tacticCharacter);
}
