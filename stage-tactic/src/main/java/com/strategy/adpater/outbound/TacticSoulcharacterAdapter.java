package com.strategy.adpater.outbound;


import com.strategy.adpater.outbound.persistence.entity.TacticSoulcharacter;
import com.strategy.adpater.outbound.persistence.repository.TacticSoulcharacterRepository;
import com.strategy.application.port.outbound.TacticSoulcharacterOutboundPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TacticSoulcharacterAdapter implements TacticSoulcharacterOutboundPort {

    private final TacticSoulcharacterRepository tacticSoulcharacterRepository;

    public TacticSoulcharacterAdapter(TacticSoulcharacterRepository tacticSoulcharacterRepository) {
        this.tacticSoulcharacterRepository = tacticSoulcharacterRepository;
    }

    @Override
    public Optional<TacticSoulcharacter> getByName(String name) {
        return tacticSoulcharacterRepository.getByName(name);
    }

    @Override
    public void save(TacticSoulcharacter tacticSoulcharacter) {
        tacticSoulcharacterRepository.save(tacticSoulcharacter);
    }

    @Override
    public void deleteById(Long id) {
        tacticSoulcharacterRepository.deleteById(id);
    }

    @Override
    public Optional<TacticSoulcharacter> findById(Long id) {
        return tacticSoulcharacterRepository.findById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return tacticSoulcharacterRepository.existsByName(name);
    }

    @Override
    public TacticSoulcharacter getByReferenceId(Long id) {
        return tacticSoulcharacterRepository.getReferenceById(id);
    }


}
