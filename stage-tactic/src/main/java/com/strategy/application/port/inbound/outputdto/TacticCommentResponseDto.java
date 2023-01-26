package com.strategy.application.port.inbound.outputdto;


import com.strategy.adpater.outbound.persistence.entity.Tactic;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TacticCommentResponseDto {

    private String username;

    private String contents;

    @Builder
    public TacticCommentResponseDto (String username, String contents){
        this.username = username;
        this.contents = contents;
    }
}
