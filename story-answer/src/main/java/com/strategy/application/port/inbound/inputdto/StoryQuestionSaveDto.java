package com.strategy.application.port.inbound.inputdto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StoryQuestionSaveDto {

    private Long storyEpisodeId;

    private int orderNumber;

    private String info;
}
