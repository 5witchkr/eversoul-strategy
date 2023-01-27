package com.strategy.application.processor;

import com.strategy.adapter.outbound.persistence.entity.StoryEpisode;
import com.strategy.adapter.outbound.persistence.entity.StorySoulcharacter;
import com.strategy.application.port.inbound.StoryEpisodeManagementInboundPort;
import com.strategy.application.port.inbound.inputdto.StoryEpisodeSaveDto;
import com.strategy.application.port.outbound.StoryEpisodeOutboundPort;
import com.strategy.application.port.outbound.StorySoulcharacterOutboundPort;
import org.springframework.stereotype.Component;


@Component
public class StoryEpisodeManagementProcessor implements StoryEpisodeManagementInboundPort {

    private final StorySoulcharacterOutboundPort storySoulcharacterOutboundPort;
    private final StoryEpisodeOutboundPort storyEpisodeOutboundPort;

    public StoryEpisodeManagementProcessor(StorySoulcharacterOutboundPort storySoulcharacterOutboundPort, StoryEpisodeOutboundPort storyEpisodeOutboundPort) {
        this.storySoulcharacterOutboundPort = storySoulcharacterOutboundPort;
        this.storyEpisodeOutboundPort = storyEpisodeOutboundPort;
    }

    @Override
    public void postStoryEpisode(StoryEpisodeSaveDto storyEpisodeSaveDto) {
        StorySoulcharacter storySoulcharacter = storySoulcharacterOutboundPort
                .findById(storyEpisodeSaveDto.getStorySoulcharacterId())
                .orElseThrow(() -> new NullPointerException("존재하지 않는 정령"));
        StoryEpisode storyEpisode = StoryEpisode.builder()
                .storySoulcharacter(storySoulcharacter)
                .title(storyEpisodeSaveDto.getTitle())
                .info(storyEpisodeSaveDto.getInfo())
                .orderNumber(storyEpisodeSaveDto.getOrderNumber())
                .build();
        storyEpisodeOutboundPort.save(storyEpisode);
    }

    @Override
    public void deleteStoryEpisode(Long id) {
        storyEpisodeOutboundPort.deleteById(id);
    }
}
