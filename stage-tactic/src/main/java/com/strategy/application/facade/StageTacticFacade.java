package com.strategy.application.facade;


import com.strategy.application.port.inbound.inputdto.TacticRequestDto;
import com.strategy.application.port.inbound.outputdto.RecommendTacticResponseDto;
import com.strategy.application.processor.RecommendProcessor;
import com.strategy.application.processor.TacticProcessor;
import com.strategy.application.validator.BanSoulsValidator;
import com.strategy.application.validator.StageParamValidator;
import com.strategy.application.validator.TacticRequestValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageTacticFacade {

    private final RecommendProcessor recommendProcessor;
    private final TacticProcessor tacticProcessor;
    private final BanSoulsValidator banSoulsValidator;
    private final StageParamValidator stageParamValidator;
    private final TacticRequestValidator tacticRequestValidator;


    public StageTacticFacade(RecommendProcessor recommendProcessor, TacticProcessor tacticProcessor, BanSoulsValidator banSoulsValidator, StageParamValidator stageParamValidator, TacticRequestValidator tacticRequestValidator) {
        this.recommendProcessor = recommendProcessor;
        this.tacticProcessor = tacticProcessor;
        this.banSoulsValidator = banSoulsValidator;
        this.stageParamValidator = stageParamValidator;
        this.tacticRequestValidator = tacticRequestValidator;
    }

    public List<RecommendTacticResponseDto> getRecommendWithoutBans(String location, String step, List<String> bans) {
        stageParamValidator.checkLocation(location);
        stageParamValidator.checkStep(step);
        List<String> filteredBans = banSoulsValidator.filterBanSouls(bans);
        return recommendProcessor.getRecommendWithoutBans(location ,step ,filteredBans);
    }

    public void postTactic(TacticRequestDto tacticRequestDto){
        tacticRequestValidator.checkTacticRequest(tacticRequestDto);
        tacticProcessor.postTactic(tacticRequestDto);
    }
}
