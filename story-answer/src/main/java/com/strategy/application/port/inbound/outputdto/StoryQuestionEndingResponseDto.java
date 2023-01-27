package com.strategy.application.port.inbound.outputdto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoryQuestionEndingResponseDto {

    private int orderNumber;
    private String info;
    private List<StoryAnswerResponseDto> storyAnswerResponseDtos;

    @Builder
    public StoryQuestionEndingResponseDto(int orderNumber, String info, List<StoryAnswerResponseDto> storyAnswerResponseDtos) {
        this.orderNumber = orderNumber;
        this.info = info;
        this.storyAnswerResponseDtos = storyAnswerResponseDtos;
    }
}
