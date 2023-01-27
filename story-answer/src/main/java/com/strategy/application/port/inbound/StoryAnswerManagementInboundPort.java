package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.inputdto.StoryAnswerSaveDto;

public interface StoryAnswerManagementInboundPort {

    void postStoryAnswer(StoryAnswerSaveDto storyAnswerSaveDto);

    void deleteStoryAnswer(Long id);
}
