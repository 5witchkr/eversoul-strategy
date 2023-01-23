package com.strategy.adapter.outbound;

import com.strategy.adapter.outbound.persistence.SoulCharacter;
import com.strategy.adapter.outbound.persistence.SoulCharacterRepository;
import com.strategy.application.port.outbound.SoulCharacterOutboundPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SoulCharacterAdapter implements SoulCharacterOutboundPort {

    private final SoulCharacterRepository soulCharacterRepository;

    public SoulCharacterAdapter(SoulCharacterRepository soulCharacterRepository) {
        this.soulCharacterRepository = soulCharacterRepository;
    }

    @Override
    public Optional<List<SoulCharacter>> getByTier(String tier) {
        return soulCharacterRepository.getByTier(tier);
    }
}
