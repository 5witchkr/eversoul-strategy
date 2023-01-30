package com.strategy.application.facade;

import com.strategy.application.port.inbound.portcomment.GetTacticCommentInboundPort;
import com.strategy.application.port.inbound.portcomment.PostTacticCommentInboundPort;
import com.strategy.application.port.inbound.outputdto.TacticCommentResponseDto;
import com.strategy.application.port.inbound.inputdto.commentdto.TacticCommentRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacticCommentFacade {

    private final GetTacticCommentInboundPort getTacticCommentInboundPort;
    private final PostTacticCommentInboundPort postTacticCommentInboundPort;

    public TacticCommentFacade(GetTacticCommentInboundPort getTacticCommentInboundPort, PostTacticCommentInboundPort postTacticCommentInboundPort) {
        this.getTacticCommentInboundPort = getTacticCommentInboundPort;
        this.postTacticCommentInboundPort = postTacticCommentInboundPort;
    }


    public void postComment(TacticCommentRequestDto commentRequestDto) {
        postTacticCommentInboundPort.postComment(commentRequestDto);
    }

    public List<TacticCommentResponseDto> getComments(Long tacticId) {
        return getTacticCommentInboundPort.getComments(tacticId);
    }
}
