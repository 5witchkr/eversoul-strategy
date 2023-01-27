package com.strategy.adapter.outbound.adapter;


import com.strategy.adapter.outbound.persistence.entity.StorySoulcharacter;
import com.strategy.adapter.outbound.persistence.repository.StorySoulcharacterRepository;
import com.strategy.application.port.outbound.StorySoulcharacterOutboundPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StorySoulcharacterAdapter implements StorySoulcharacterOutboundPort {

    private final StorySoulcharacterRepository storySoulcharacterRepository;

    public StorySoulcharacterAdapter(StorySoulcharacterRepository storySoulcharacterRepository) {
        this.storySoulcharacterRepository = storySoulcharacterRepository;
    }

    @Override
    public StorySoulcharacter getByName(String soul) {
        return storySoulcharacterRepository.getByName(soul);
    }

    @Override
    public void save(StorySoulcharacter storySoulcharacter) {
        storySoulcharacterRepository.save(storySoulcharacter);
    }

    @Override
    public void deleteById(Long id) {
        storySoulcharacterRepository.deleteById(id);
    }

    @Override
    public Optional<StorySoulcharacter> findById(Long storySoulcharacterId) {
        return storySoulcharacterRepository.findById(storySoulcharacterId);
    }
}
