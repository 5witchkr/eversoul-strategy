package com.strategy.application.processor;

import com.strategy.adapter.outbound.persistence.entity.StoryAnswer;
import com.strategy.adapter.outbound.persistence.entity.StoryQuestion;
import com.strategy.application.port.inbound.StoryAnswerManagementInboundPort;
import com.strategy.application.port.inbound.inputdto.StoryAnswerSaveDto;
import com.strategy.application.port.outbound.StoryAnswerOutboundPort;
import com.strategy.application.port.outbound.StoryQuestionOutboundPort;
import org.springframework.stereotype.Component;


@Component
public class StoryAnswerManagementProcessor implements StoryAnswerManagementInboundPort {

    private final StoryAnswerOutboundPort storyAnswerOutboundPort;
    private final StoryQuestionOutboundPort storyQuestionOutboundPort;

    public StoryAnswerManagementProcessor(StoryAnswerOutboundPort storyAnswerOutboundPort, StoryQuestionOutboundPort storyQuestionOutboundPort) {
        this.storyAnswerOutboundPort = storyAnswerOutboundPort;
        this.storyQuestionOutboundPort = storyQuestionOutboundPort;
    }

    @Override
    public void postStoryAnswer(StoryAnswerSaveDto storyAnswerSaveDto) {
        StoryQuestion storyQuestion = storyQuestionOutboundPort.findById(storyAnswerSaveDto.getStoryQuestionId())
                .orElseThrow(() -> new NullPointerException("존재하지 않는 질문"));
        StoryAnswer storyAnswer = StoryAnswer.builder()
                .storyQuestion(storyQuestion)
                .orderNumber(storyAnswerSaveDto.getOrderNumber())
                .bestAnswer(storyAnswerSaveDto.getBestAnswer())
                .info(storyAnswerSaveDto.getInfo())
                .title(storyAnswerSaveDto.getTitle())
                .build();
        storyAnswerOutboundPort.save(storyAnswer);
    }

    @Override
    public void deleteStoryAnswer(Long id) {
        storyAnswerOutboundPort.deleteById(id);
    }
}
