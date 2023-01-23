package com.strategy.application.port.outbound;

import com.strategy.adpater.outbound.persistence.entity.Tactic;

public interface TacticOutboundPort {

    Tactic save(Tactic tactic);
}
