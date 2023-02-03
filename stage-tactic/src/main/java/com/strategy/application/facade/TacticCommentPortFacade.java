package com.strategy.application.facade;

import com.strategy.application.port.inbound.inputdto.commentdto.TacticCommentRequestDto;
import com.strategy.application.port.inbound.outputdto.TacticCommentResponseDto;

import java.util.List;

public interface TacticCommentPortFacade {

    void postComment(TacticCommentRequestDto commentRequestDto);


    List<TacticCommentResponseDto> getComments(Long tacticId);

}
