package com.strategy.application.facade;


import com.strategy.application.port.inbound.inputdto.TacticRequestDto;
import com.strategy.application.port.inbound.outputdto.RecommendTacticResponseDto;
import com.strategy.application.processor.RecommendProcessor;
import com.strategy.application.processor.TacticProcessor;
import com.strategy.application.validator.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageTacticFacade {

    private final RecommendProcessor recommendProcessor;
    private final TacticProcessor tacticProcessor;
    private final BanSoulsValidator banSoulsValidator;
    private final StageParamValidator stageParamValidator;
    private final InfoValidator infoValidator;
    private final LevelValidator levelValidator;
    private final PositionValidator positionValidator;
    private final PowerValidator powerValidator;
    private final SoulNameValidator soulNameValidator;



    public StageTacticFacade(RecommendProcessor recommendProcessor, TacticProcessor tacticProcessor, BanSoulsValidator banSoulsValidator, StageParamValidator stageParamValidator, InfoValidator infoValidator, LevelValidator levelValidator, PositionValidator positionValidator, PowerValidator powerValidator, SoulNameValidator soulNameValidator) {
        this.recommendProcessor = recommendProcessor;
        this.tacticProcessor = tacticProcessor;
        this.banSoulsValidator = banSoulsValidator;
        this.stageParamValidator = stageParamValidator;
        this.infoValidator = infoValidator;
        this.levelValidator = levelValidator;
        this.positionValidator = positionValidator;
        this.powerValidator = powerValidator;
        this.soulNameValidator = soulNameValidator;
    }

    public List<RecommendTacticResponseDto> getRecommendWithoutBans(int location, int step, List<String> bans) {
        stageParamValidator.checkLocation(location);
        stageParamValidator.checkStep(step);
        List<String> filteredBans = banSoulsValidator.filterBanSouls(bans);
        return recommendProcessor.getRecommendWithoutBans(location ,step ,filteredBans);
    }

    public void postTactic(TacticRequestDto tacticRequestDto){
        stageParamValidator.checkLocation(tacticRequestDto.getLocation());
        stageParamValidator.checkStep(tacticRequestDto.getStep());
        infoValidator.checkInfo(tacticRequestDto.getInfo());
        positionValidator.checkPosition(tacticRequestDto.getPosition());
        powerValidator.checkPower(tacticRequestDto.getPower());
        levelValidator.checkLevelByDtos(tacticRequestDto.getSoulCharacters());
        soulNameValidator.checkSoulNameByDtos(tacticRequestDto.getSoulCharacters());
        soulNameValidator.checkDuplicateSoul(tacticRequestDto.getSoulCharacters());
        tacticProcessor.postTactic(tacticRequestDto);
    }
}
