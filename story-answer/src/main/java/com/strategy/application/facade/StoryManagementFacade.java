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
public class StoryManagementFacade implements StoryManagementPortFacade{

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


    @Override
    public void postSoul(StorySoulSaveDto soulSaveDto) {
        storySoulManagementInboundPort.postStorySoul(soulSaveDto);
    }

    @Override
    public void postEpisode(StoryEpisodeSaveDto storyEpisodeSaveDto) {
        storyEpisodeManagementInboundPort.postStoryEpisode(storyEpisodeSaveDto);
    }

    @Override
    public void postQuestion(StoryQuestionSaveDto storyQuestionSaveDto) {
        storyQuestionManagementInboundPort.postStoryQuestion(storyQuestionSaveDto);
    }

    @Override
    public void postAnswer(StoryAnswerSaveDto storyAnswerSaveDto) {
        storyAnswerManagementInboundPort.postStoryAnswer(storyAnswerSaveDto);
    }

    @Override
    public void deleteSoul(Long id) {
        storySoulManagementInboundPort.deleteStorySoul(id);
    }

    @Override
    public void deleteEpisode(Long id) {
        storyEpisodeManagementInboundPort.deleteStoryEpisode(id);
    }

    @Override
    public void deleteQuestion(Long id) {
        storyQuestionManagementInboundPort.deleteStoryQuestion(id);
    }

    @Override
    public void deleteAnswer(Long id) {
        storyAnswerManagementInboundPort.deleteStoryAnswer(id);
    }
}

