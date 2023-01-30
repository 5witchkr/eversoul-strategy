package com.strategy.application.port.inbound.portcomment;

import com.strategy.application.port.inbound.inputdto.commentdto.TacticCommentRequestDto;

public interface PostTacticCommentInboundPort {

    void postComment(TacticCommentRequestDto tacticCommentRequestDto);
}
