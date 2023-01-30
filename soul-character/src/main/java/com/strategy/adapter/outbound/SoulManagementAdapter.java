package com.strategy.adapter.outbound;

import com.strategy.adapter.outbound.persistence.SoulCharacter;
import com.strategy.adapter.outbound.persistence.SoulCharacterRepository;
import com.strategy.application.port.outbound.SoulManagementOutboundPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SoulManagementAdapter implements SoulManagementOutboundPort {

    private final SoulCharacterRepository soulCharacterRepository;

    public SoulManagementAdapter(SoulCharacterRepository soulCharacterRepository) {
        this.soulCharacterRepository = soulCharacterRepository;
    }

    @Override
    public SoulCharacter save(SoulCharacter soulCharacter) {
        return soulCharacterRepository.save(soulCharacter);
    }

    @Override
    public Optional<SoulCharacter> findById(Long id) {
        return soulCharacterRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        soulCharacterRepository.deleteById(id);
    }
}
