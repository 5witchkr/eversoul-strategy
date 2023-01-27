package com.strategy.application.port.inbound.inputdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryEpisodeSaveDto {

    private int orderNumber;

    private String title;

    private String info;

    private Long storySoulcharacterId;
}