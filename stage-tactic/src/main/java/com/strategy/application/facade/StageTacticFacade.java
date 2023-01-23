package com.strategy.application.facade;


import com.strategy.application.port.inbound.inputdto.TacticRequestDto;
import com.strategy.application.port.inbound.outputdto.RecommendTacticResponseDto;
import com.strategy.application.processor.RecommendProcessor;
import com.strategy.application.processor.TacticProcessor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageTacticFacade {

    private final RecommendProcessor recommendProcessor;
    private final TacticProcessor tacticProcessor;


    public StageTacticFacade(RecommendProcessor recommendProcessor, TacticProcessor tacticProcessor) {
        this.recommendProcessor = recommendProcessor;
        this.tacticProcessor = tacticProcessor;
    }

    public List<RecommendTacticResponseDto> getRecommendWithoutBans(String location, String step, List<String> bans) {
        return recommendProcessor.getRecommendWithoutBans(location ,step ,bans);
    }

    public void postTactic(TacticRequestDto tacticRequestDto){
        tacticProcessor.postTactic(tacticRequestDto);
    }
}
