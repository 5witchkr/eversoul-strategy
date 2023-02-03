package com.strategy.application.facade;

import com.strategy.application.port.inbound.inputdto.tacticdto.TacticRequestDto;
import com.strategy.application.port.inbound.outputdto.RecommendTacticResponseDto;

import java.util.List;

public interface StageTacticPortFacade {

    List<RecommendTacticResponseDto> getRecommendWithoutBans(int location, int step, List<String> bans);

    void postTactic(TacticRequestDto tacticRequestDto);
}
