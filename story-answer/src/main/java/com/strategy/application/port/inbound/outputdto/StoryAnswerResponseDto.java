package com.strategy.application.port.inbound.outputdto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryAnswerResponseDto {

    private int orderNumber;

    private String title;

    private String info;

    @Builder
    public StoryAnswerResponseDto(int orderNumber, String title, String info) {
        this.orderNumber = orderNumber;
        this.title = title;
        this.info = info;
    }
}
