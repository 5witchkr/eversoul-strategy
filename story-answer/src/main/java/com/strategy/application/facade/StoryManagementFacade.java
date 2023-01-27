package com.strategy.application.facade;

import com.strategy.application.port.inbound.StoryAnswerManagementInboundPort;
import com.strategy.application.port.inbound.StoryEpisodeManagementInboundPort;
import com.strategy.application.port.inbound.StoryQuestionManagementInboundPort;
import com.strategy.application.port.inbound.StorySoulManagementInboundPort;
import com.strategy.application.port.inbound.inputdto.StoryAnswerSaveDto;
import com.strategy.application.port.inbound.inputdto.StoryEpisodeSaveDto;
import com.strategy.application.port.inbound.inputdto.StoryQuestionSaveDto;
import com.strategy.application.port.inbound.inputdto.StorySoulSaveDto;
import org.springframework.stereotype.Service;


@Service
public class StoryManagementFacade {

    private final StoryAnswerManagementInboundPort storyAnswerManagementInboundPort;
    private final StoryQuestionManagementInboundPort storyQuestionManagementInboundPort;
    private final StoryEpisodeManagementInboundPort storyEpisodeManagementInboundPort;
    private final StorySoulManagementInboundPort storySoulManagementInboundPort;

    public StoryManagementFacade(StoryAnswerManagementInboundPort storyAnswerManagementInboundPort,
                                 StoryQuestionManagementInboundPort storyQuestionManagementInboundPort,
                                 StoryEpisodeManagementInboundPort storyEpisodeManagementInboundPort,
                                 StorySoulManagementInboundPort storySoulManagementInboundPort) {
        this.storyAnswerManagementInboundPort = storyAnswerManagementInboundPort;
        this.storyQuestionManagementInboundPort = storyQuestionManagementInboundPort;
        this.storyEpisodeManagementInboundPort = storyEpisodeManagementInboundPort;
        this.storySoulManagementInboundPort = storySoulManagementInboundPort;
    }

    public void postSoul(StorySoulSaveDto soulSaveDto) {
        storySoulManagementInboundPort.postStorySoul(soulSaveDto);
    }

    public void postEpisode(StoryEpisodeSaveDto storyEpisodeSaveDto) {
        storyEpisodeManagementInboundPort.postStoryEpisode(storyEpisodeSaveDto);
    }

    public void postQuestion(StoryQuestionSaveDto storyQuestionSaveDto) {
        storyQuestionManagementInboundPort.postStoryQuestion(storyQuestionSaveDto);
    }

    public void postAnswer(StoryAnswerSaveDto storyAnswerSaveDto) {
        storyAnswerManagementInboundPort.postStoryAnswer(storyAnswerSaveDto);
    }

    public void deleteSoul(Long id) {
        storySoulManagementInboundPort.deleteStorySoul(id);
    }

    public void deleteEpisode(Long id) {
        storyEpisodeManagementInboundPort.deleteStoryEpisode(id);
    }

    public void deleteQuestion(Long id) {
        storyQuestionManagementInboundPort.deleteStoryQuestion(id);
    }

    public void deleteAnswer(Long id) {
        storyAnswerManagementInboundPort.deleteStoryAnswer(id);
    }
}
