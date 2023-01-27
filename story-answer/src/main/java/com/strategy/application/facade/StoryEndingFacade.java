package com.strategy.application.facade;

import com.strategy.application.port.inbound.outputdto.StoryQuestionEndingResponseDto;
import com.strategy.application.port.inbound.GetSoulEndingByEpisodeInboundPort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StoryEndingFacade {

    private final GetSoulEndingByEpisodeInboundPort getSoulEndingByEpisodeInboundPort;

    public StoryEndingFacade(GetSoulEndingByEpisodeInboundPort getSoulEndingByEpisodeInboundPort) {
        this.getSoulEndingByEpisodeInboundPort = getSoulEndingByEpisodeInboundPort;
    }

    public List<StoryQuestionEndingResponseDto> getSoulEndingByEpisode(String soul, int episode, String ending) {
        if (episode == 0) {
            return getSoulEndingByEpisodeInboundPort.getSoulEndingAllEpisode(soul, ending);
        }
        return getSoulEndingByEpisodeInboundPort.getSoulEndingByEpisode(soul, episode, ending);
    }
}
