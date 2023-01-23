package com.strategy.adpater.outbound;


import com.strategy.adpater.outbound.persistence.entity.Tactic;
import com.strategy.adpater.outbound.persistence.repository.TacticRepository;
import com.strategy.application.port.outbound.TacticOutboundPort;
import org.springframework.stereotype.Component;

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
}
