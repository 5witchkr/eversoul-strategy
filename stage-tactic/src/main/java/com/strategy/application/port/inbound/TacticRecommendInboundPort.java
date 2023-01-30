package com.strategy.application.port.inbound;

public interface TacticRecommendInboundPort {

    void addRecommend(Long tacticID, String userIp);
}
