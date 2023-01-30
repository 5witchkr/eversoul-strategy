package com.strategy.application.port.inbound.inputdto.commentdto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TacticCommentRequestDto {

    @NotNull
    @Positive
    private Long tacticId;

    @NotBlank
    @Size(min = 1, max = 15)
    private String username;

    @NotBlank
    @Size(min = 1, max = 150)
    private String contents;


    @Builder
    public TacticCommentRequestDto(Long tacticId, String username, String contents){
        this.tacticId = tacticId;
        this.username = username;
        this.contents = contents;
    }

}
