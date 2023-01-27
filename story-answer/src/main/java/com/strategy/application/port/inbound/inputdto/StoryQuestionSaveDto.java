package com.strategy.application.port.inbound.inputdto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StoryQuestionSaveDto {

    private int orderNumber;

    private String info;

    private Long StoryEpisodeId;
}
