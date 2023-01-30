package com.strategy.application.processor;

import com.strategy.adpater.outbound.persistence.entity.Tactic;
import com.strategy.application.port.inbound.portrecommend.TacticRecommendInboundPort;
import com.strategy.application.port.outbound.TacticOutboundPort;
import org.springframework.stereotype.Component;


@Component
public class TacticRecommendCountProcessor implements TacticRecommendInboundPort {

    private final TacticOutboundPort tacticOutboundPort;


    public TacticRecommendCountProcessor(TacticOutboundPort tacticOutboundPort) {
        this.tacticOutboundPort = tacticOutboundPort;
    }

    @Override
    public void addRecommend(Long tacticId) {
        try {
            Tactic tactic = tacticOutboundPort.getReferenceById(tacticId);
            tactic.setRecommendCount(tactic.getRecommendCount() + 1);

        } catch (RuntimeException runtimeException) {
            throw new IllegalArgumentException("존재하지 않는 게시글");
        }
    }
}
