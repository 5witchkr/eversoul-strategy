package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.inputdto.StoryQuestionSaveDto;

public interface StoryQuestionManagementInboundPort {

    void postStoryQuestion(StoryQuestionSaveDto storyQuestionSaveDto);

    void deleteStoryQuestion(Long id);
}
