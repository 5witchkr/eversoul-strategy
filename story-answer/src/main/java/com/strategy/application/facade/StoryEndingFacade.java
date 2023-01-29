package com.strategy.application.facade;

import com.strategy.application.port.inbound.outputdto.StoryQuestionEndingResponseDto;
import com.strategy.application.port.inbound.GetSoulEndingByEpisodeInboundPort;
import com.strategy.application.validator.StoryEndingValidoatr;
import com.strategy.application.validator.StoryEpisodeValidator;
import com.strategy.application.validator.StorySoulIdValidator;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StoryEndingFacade {

    private final GetSoulEndingByEpisodeInboundPort getSoulEndingByEpisodeInboundPort;
    private final StoryEpisodeValidator storyEpisodeValidator;
    private final StoryEndingValidoatr storyEndingValidoatr;
    private final StorySoulIdValidator storySoulIdValidator;

    public StoryEndingFacade(GetSoulEndingByEpisodeInboundPort getSoulEndingByEpisodeInboundPort, StoryEpisodeValidator storyEpisodeValidator, StoryEndingValidoatr storyEndingValidoatr, StorySoulIdValidator storySoulIdValidator) {
        this.getSoulEndingByEpisodeInboundPort = getSoulEndingByEpisodeInboundPort;
        this.storyEpisodeValidator = storyEpisodeValidator;
        this.storyEndingValidoatr = storyEndingValidoatr;
        this.storySoulIdValidator = storySoulIdValidator;
    }

    public List<StoryQuestionEndingResponseDto> getSoulEndingByEpisode(Long soulId, int episode, String ending) {
        storyEpisodeValidator.checkEpisode(episode);
        storyEndingValidoatr.checkEndingValue(ending);
        storySoulIdValidator.checkSoulId(soulId);
        if (episode == 0) {
            return getSoulEndingByEpisodeInboundPort.getSoulEndingAllEpisode(soulId, ending);
        }
        return getSoulEndingByEpisodeInboundPort.getSoulEndingByEpisode(soulId, episode, ending);
    }
}
