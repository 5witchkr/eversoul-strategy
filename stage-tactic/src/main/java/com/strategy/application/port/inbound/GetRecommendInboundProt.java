package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.outputdto.RecommendTacticResponseDto;

import java.util.List;

public interface GetRecommendInboundProt {

    List<RecommendTacticResponseDto> getRecommendWithoutBans(int location, int step, List<String> bans);
}
