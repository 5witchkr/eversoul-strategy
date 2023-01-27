package com.strategy.application.port.inbound.inputdto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StoryAnswerSaveDto {

    private Long storyQuestionId;

    private int orderNumber;

    private int bestAnswer;//0 = worst,  1 = best

    private String title;

    private String info;
}
