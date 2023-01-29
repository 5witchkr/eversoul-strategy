package com.strategy.application.processor;

import com.strategy.adapter.outbound.persistence.entity.StoryAnswer;
import com.strategy.adapter.outbound.persistence.entity.StoryQuestion;
import com.strategy.adapter.outbound.persistence.entity.StorySoulcharacter;
import com.strategy.application.port.inbound.GetSoulEndingByEpisodeInboundPort;
import com.strategy.application.port.inbound.outputdto.StoryAnswerResponseDto;
import com.strategy.application.port.inbound.outputdto.StoryQuestionEndingResponseDto;
import com.strategy.application.port.outbound.StoryAnswerOutboundPort;
import com.strategy.application.port.outbound.StoryEpisodeOutboundPort;
import com.strategy.application.port.outbound.StoryQuestionOutboundPort;
import com.strategy.application.port.outbound.StorySoulcharacterOutboundPort;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class GetSoulEndingByEpisodeProcessor implements GetSoulEndingByEpisodeInboundPort {

    private final StorySoulcharacterOutboundPort storySoulcharacterOutboundPort;
    private final StoryEpisodeOutboundPort storyEpisodeOutboundPort;


    public GetSoulEndingByEpisodeProcessor(StorySoulcharacterOutboundPort storySoulcharacterOutboundPort,
                                           StoryEpisodeOutboundPort storyEpisodeOutboundPort) {
        this.storySoulcharacterOutboundPort = storySoulcharacterOutboundPort;
        this.storyEpisodeOutboundPort = storyEpisodeOutboundPort;
    }

    @Override
    public List<StoryQuestionEndingResponseDto> getSoulEndingAllEpisode(Long soulId, String ending) {

        StorySoulcharacter storySoulcharacter =storySoulcharacterOutboundPort.getByReferenceId(soulId);

        return storyEpisodeOutboundPort.getByOrderNumberAndStorySoulcharacter(0, storySoulcharacter)
                .getStoryQuestions().stream()
                .map(this::storyQuestionToEndingResDto)
                .sorted(Comparator.comparing(StoryQuestionEndingResponseDto::getOrderNumber))
                .collect(Collectors.toList());
    }

    private StoryQuestionEndingResponseDto storyQuestionToEndingResDto(StoryQuestion storyQuestion) {
        if (storyQuestion == null) {
            return null;
        }
        StoryQuestionEndingResponseDto.StoryQuestionEndingResponseDtoBuilder
                storyQuestionEndingResponseDto = StoryQuestionEndingResponseDto.builder();
        storyQuestionEndingResponseDto.orderNumber(storyQuestion.getOrderNumber());
        storyQuestionEndingResponseDto.info(storyQuestion.getInfo());
        storyQuestionEndingResponseDto.storyAnswerResponseDtos(getBestAnswerDto(storyQuestion));
        return storyQuestionEndingResponseDto.build();
    }

    private List<StoryAnswerResponseDto> getBestAnswerDto(StoryQuestion storyQuestion) {
        return storyQuestion.getStoryAnswers().stream()
                .filter(storyAnswer -> storyAnswer.getBestAnswer() > 0)
                .map(this::answerToAnswerResDto)
                .sorted(Comparator.comparing(StoryAnswerResponseDto::getOrderNumber))
                .collect(Collectors.toList());
    }

    private StoryAnswerResponseDto answerToAnswerResDto(StoryAnswer storyAnswer) {
        if (storyAnswer == null) {
            return null;
        }
        StoryAnswerResponseDto.StoryAnswerResponseDtoBuilder
                storyAnswerResponseDto = StoryAnswerResponseDto.builder();
        storyAnswerResponseDto.orderNumber(storyAnswer.getOrderNumber());
        storyAnswerResponseDto.title(storyAnswer.getTitle());
        storyAnswerResponseDto.info(storyAnswer.getInfo());
        return storyAnswerResponseDto.build();
    }


    @Override
    public List<StoryQuestionEndingResponseDto> getSoulEndingByEpisode(Long soulId, int episode, String ending) {
        return null;
    }
}
