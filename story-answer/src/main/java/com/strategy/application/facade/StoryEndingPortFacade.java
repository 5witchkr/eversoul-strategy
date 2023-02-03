package com.strategy.application.facade;

import com.strategy.application.port.inbound.outputdto.StoryQuestionEndingResponseDto;

import java.util.List;

public interface StoryEndingPortFacade {

    List<StoryQuestionEndingResponseDto> getSoulEndingByEpisode(Long soulId, int episode, String ending);
}
