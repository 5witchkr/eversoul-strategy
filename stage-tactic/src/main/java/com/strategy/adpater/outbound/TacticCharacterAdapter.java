package com.strategy.adpater.outbound;


import com.strategy.adpater.outbound.persistence.entity.TacticCharacter;
import com.strategy.adpater.outbound.persistence.repository.TacticCharacterRepository;
import com.strategy.application.port.outbound.TacticCharacterOutboundPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TacticCharacterAdapter implements TacticCharacterOutboundPort {

    private final TacticCharacterRepository tacticCharacterRepository;

    public TacticCharacterAdapter(TacticCharacterRepository tacticCharacterRepository) {
        this.tacticCharacterRepository = tacticCharacterRepository;
    }

    @Override
    public TacticCharacter save(TacticCharacter tacticCharacter) {
        return tacticCharacterRepository.save(tacticCharacter);
    }

    @Override
    public List<TacticCharacter> saveAll(List<TacticCharacter> tacticCharacters) {
        return tacticCharacterRepository.saveAll(tacticCharacters);
    }

    @Override
    public Long countAll() {
        return tacticCharacterRepository.count();
    }
}
