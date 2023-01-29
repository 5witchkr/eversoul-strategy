package com.strategy.application.port.inbound;


import com.strategy.application.port.inbound.outputdto.StoryQuestionEndingResponseDto;

import java.util.List;

public interface GetSoulEndingByEpisodeInboundPort {


    List<StoryQuestionEndingResponseDto> getSoulEndingAllEpisode(Long soulId, String ending);

    List<StoryQuestionEndingResponseDto> getSoulEndingByEpisode(Long soulId,int episode, String ending);

}
