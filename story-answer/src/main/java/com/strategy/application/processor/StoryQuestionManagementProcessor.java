package com.strategy.application.processor;

import com.strategy.adapter.outbound.persistence.entity.StoryEpisode;
import com.strategy.adapter.outbound.persistence.entity.StoryQuestion;
import com.strategy.application.port.inbound.StoryQuestionManagementInboundPort;
import com.strategy.application.port.inbound.inputdto.StoryQuestionSaveDto;
import com.strategy.application.port.outbound.StoryEpisodeOutboundPort;
import com.strategy.application.port.outbound.StoryQuestionOutboundPort;
import org.springframework.stereotype.Component;


@Component
public class StoryQuestionManagementProcessor implements StoryQuestionManagementInboundPort {

    private final StoryQuestionOutboundPort storyQuestionOutboundPort;
    private final StoryEpisodeOutboundPort storyEpisodeOutboundPort;

    public StoryQuestionManagementProcessor(StoryQuestionOutboundPort storyQuestionOutboundPort, StoryEpisodeOutboundPort storyEpisodeOutboundPort) {
        this.storyQuestionOutboundPort = storyQuestionOutboundPort;
        this.storyEpisodeOutboundPort = storyEpisodeOutboundPort;
    }

    @Override
    public void postStoryQuestion(StoryQuestionSaveDto storyQuestionSaveDto) {
        StoryEpisode storyEpisode = storyEpisodeOutboundPort
                .findById(storyQuestionSaveDto.getStoryEpisodeId())
                .orElseThrow(() -> new NullPointerException("존재하지 않는 에피소드"));
        StoryQuestion storyQuestion = StoryQuestion.builder()
                .storyEpisode(storyEpisode)
                .orderNumber(storyQuestionSaveDto.getOrderNumber())
                .info(storyQuestionSaveDto.getInfo())
                .build();
        storyQuestionOutboundPort.save(storyQuestion);
    }

    @Override
    public void deleteStoryQuestion(Long id) {
        storyQuestionOutboundPort.deleteById(id);
    }
}
