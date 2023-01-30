package com.strategy.application.port.inbound.portrecommend;

public interface TacticRecommendCheckInboundPort {

    Boolean checkExistIpAtTacticId(String tacticId, String userIp, String date);
}
