package com.strategy.application.processor;

import com.strategy.adpater.outbound.persistence.entity.Tactic;
import com.strategy.application.port.inbound.TacticRecommendInboundPort;
import com.strategy.application.port.outbound.TacticOutboundPort;
import com.strategy.application.port.outbound.RedisTacticRecommendOutboundPort;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Component
public class TacticRecommendCountProcessor implements TacticRecommendInboundPort {

    private final TacticOutboundPort tacticOutboundPort;
    private final RedisTacticRecommendOutboundPort redisTacticRecommendOutboundPort;


    public TacticRecommendCountProcessor(TacticOutboundPort tacticOutboundPort,
                                         RedisTacticRecommendOutboundPort redisTacticRecommendOutboundPort1) {
        this.tacticOutboundPort = tacticOutboundPort;
        this.redisTacticRecommendOutboundPort = redisTacticRecommendOutboundPort1;
    }

    @Override
    @Transactional
    public void addRecommend(Long tacticId, String userIp) {
        try {
            if (!checkExistIpAtTacticId(String.valueOf(tacticId), userIp, LocalDate.now().toString())) {
                Tactic tactic = tacticOutboundPort.getReferenceById(tacticId);
                tactic.setRecommendCount(tactic.getRecommendCount() + 1);
            }
        } catch (RuntimeException runtimeException) {
            throw new IllegalArgumentException("존재하지 않는 게시글");
        }
    }

    private Boolean checkExistIpAtTacticId(String tacticId, String userIp, String date){
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
