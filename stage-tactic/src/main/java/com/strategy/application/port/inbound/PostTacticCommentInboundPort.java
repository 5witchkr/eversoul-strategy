package com.strategy.application.port.inbound;

import com.strategy.application.port.inbound.inputdto.TacticCommentRequestDto;

public interface PostTacticCommentInboundPort {

    void postComment(TacticCommentRequestDto tacticCommentRequestDto);
}
