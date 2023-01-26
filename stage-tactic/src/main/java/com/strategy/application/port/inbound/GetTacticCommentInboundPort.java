package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.outputdto.TacticCommentResponseDto;

import java.util.List;

public interface GetTacticCommentInboundPort {

    List<TacticCommentResponseDto> getComments(Long tacticId);
}
