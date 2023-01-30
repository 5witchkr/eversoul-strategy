package com.strategy.application.processor;


import com.strategy.application.port.outbound.RedisTacticRecommendOutboundPort;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RecommendCountProcessor {


    private final RedisTacticRecommendOutboundPort redisTacticRecommendOutboundPort;

    public RecommendCountProcessor(RedisTacticRecommendOutboundPort redisTacticRecommendOutboundPort) {
        this.redisTacticRecommendOutboundPort = redisTacticRecommendOutboundPort;
    }


    public Boolean checkExistIpAtTacticId(String tacticId, String userIp, String date){

        Set<String> recommendedIps = redisTacticRecommendOutboundPort.getRecommendedIps("tactic:"+tacticId+":ips:"+date);

        if (!recommendedIps.contains(userIp)) {
            redisTacticRecommendOutboundPort.addOpsForSet("tactic:"+tacticId+":ips:"+date, userIp);
            redisTacticRecommendOutboundPort.setExpire("tactic:"+tacticId+":ips:"+date, 1, TimeUnit.DAYS);
            return false;
        }
        redisTacticRecommendOutboundPort.setExpire("tactic:"+tacticId+":ips:"+date, 1, TimeUnit.DAYS);
        return true;
    }
}
