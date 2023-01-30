package com.strategy.application.facade;


import com.strategy.application.port.inbound.TacticRecommendInboundPort;
import org.springframework.stereotype.Service;

@Service
public class TacticRecommendFacade {

    private final TacticRecommendInboundPort tacticRecommendInboundPort;

    public TacticRecommendFacade(TacticRecommendInboundPort tacticRecommendInboundPort) {
        this.tacticRecommendInboundPort = tacticRecommendInboundPort;
    }

    public void postRecommend(Long tacticId, String userIp) {
        tacticRecommendInboundPort.addRecommend(tacticId, userIp);
    }
}
