package com.strategy.adpater.outbound;


import com.strategy.adpater.outbound.persistence.entity.Tactic;
import com.strategy.adpater.outbound.persistence.repository.TacticRepository;
import com.strategy.application.port.outbound.TacticOutboundPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TacticAdapter implements TacticOutboundPort {

    private final TacticRepository tacticRepository;

    public TacticAdapter(TacticRepository tacticRepository) {
        this.tacticRepository = tacticRepository;
    }

    @Override
    public Tactic save(Tactic tactic) {
        return tacticRepository.save(tactic);
    }

    @Override
    public void deleteById(Long id) {
        tacticRepository.deleteById(id);
    }

    @Override
    public Tactic getById(Long tacticId) {
        return tacticRepository.getById(tacticId);
    }

    @Override
    public Optional<Tactic> findById(Long tacticId) {
        return tacticRepository.findById(tacticId);
    }

    @Override
    public Tactic getReferenceById(Long tacticId) {
        return tacticRepository.getReferenceById(tacticId);
    }

    @Override
    public Long countAll() {
        return tacticRepository.count();
    }
}
