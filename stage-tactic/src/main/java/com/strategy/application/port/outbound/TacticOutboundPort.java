package com.strategy.application.port.outbound;

import com.strategy.adpater.outbound.persistence.entity.Tactic;

import java.util.Optional;

public interface TacticOutboundPort {

    Tactic save(Tactic tactic);

    void deleteById(Long id);

    /**
     * @deprecated use getReferenceById instead of getById
     * @param tacticId
     * @return
     */
    Tactic getById(Long tacticId);

    Optional<Tactic> findById(Long tacticId);

    Tactic getReferenceById(Long tacticId);
}
