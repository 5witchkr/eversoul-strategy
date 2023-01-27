package com.strategy.application.port.inbound;


import com.strategy.application.port.inbound.outputdto.StoryQuestionEndingResponseDto;

import java.util.List;

public interface GetSoulEndingByEpisodeInboundPort {


    List<StoryQuestionEndingResponseDto> getSoulEndingAllEpisode(String soul, String ending);

    List<StoryQuestionEndingResponseDto> getSoulEndingByEpisode(String soul,int episode, String ending);

}
