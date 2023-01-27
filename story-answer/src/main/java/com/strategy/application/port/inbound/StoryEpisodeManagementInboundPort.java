package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.inputdto.StoryEpisodeSaveDto;

public interface StoryEpisodeManagementInboundPort {

    void postStoryEpisode(StoryEpisodeSaveDto storyEpisodeSaveDto);

    void deleteStoryEpisode(Long id);
}
