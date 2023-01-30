package com.strategy.application.facade;


import com.strategy.application.port.inbound.portrecommend.TacticRecommendCheckInboundPort;
import com.strategy.application.port.inbound.portrecommend.TacticRecommendInboundPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class TacticRecommendFacade {

    private final TacticRecommendInboundPort tacticRecommendInboundPort;
    private final TacticRecommendCheckInboundPort tacticRecommendCheckInboundPort;

    public TacticRecommendFacade(TacticRecommendInboundPort tacticRecommendInboundPort, TacticRecommendCheckInboundPort tacticRecommendCheckInboundPort) {
        this.tacticRecommendInboundPort = tacticRecommendInboundPort;
        this.tacticRecommendCheckInboundPort = tacticRecommendCheckInboundPort;
    }

    @Transactional
    public void postRecommend(Long tacticId, String userIp) {
        if (!tacticRecommendCheckInboundPort
                .checkExistIpAtTacticId(String.valueOf(tacticId), userIp, LocalDate.now().toString())){
            tacticRecommendInboundPort.addRecommend(tacticId);
        }
    }
}
