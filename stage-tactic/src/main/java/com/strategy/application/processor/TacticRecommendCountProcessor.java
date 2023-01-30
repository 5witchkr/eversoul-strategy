package com.strategy.application.processor;

import com.strategy.adpater.outbound.persistence.entity.Tactic;
import com.strategy.application.port.inbound.TacticRecommendInboundPort;
import com.strategy.application.port.outbound.TacticOutboundPort;
import com.strategy.application.port.outbound.RedisTacticRecommendOutboundPort;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;


@Component
public class TacticRecommendCountProcessor implements TacticRecommendInboundPort {

    private final TacticOutboundPort tacticOutboundPort;
    private final RecommendCountProcessor recommendCountProcessor;


    public TacticRecommendCountProcessor(TacticOutboundPort tacticOutboundPort,
                                         RedisTacticRecommendOutboundPort redisTacticRecommendOutboundPort, RecommendCountProcessor recommendCountProcessor) {
        this.tacticOutboundPort = tacticOutboundPort;
        this.recommendCountProcessor = recommendCountProcessor;
    }

    @Override
    @Transactional
    public void addRecommend(Long tacticId, String userIp) {
        try {
            if (!recommendCountProcessor.checkExistIpAtTacticId(String.valueOf(tacticId), userIp, LocalDate.now().toString())) {
                Tactic tactic = tacticOutboundPort.getReferenceById(tacticId);
                tactic.setRecommendCount(tactic.getRecommendCount() + 1);
            }
        } catch (RuntimeException runtimeException) {
            throw new IllegalArgumentException("존재하지 않는 게시글");
        }
    }
}
