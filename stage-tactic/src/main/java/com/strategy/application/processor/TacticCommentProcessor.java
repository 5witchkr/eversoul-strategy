package com.strategy.application.processor;

import com.strategy.adpater.outbound.persistence.entity.Tactic;
import com.strategy.adpater.outbound.persistence.entity.TacticComment;
import com.strategy.application.port.inbound.portcomment.GetTacticCommentInboundPort;
import com.strategy.application.port.inbound.portcomment.PostTacticCommentInboundPort;
import com.strategy.application.port.inbound.inputdto.commentdto.TacticCommentRequestDto;
import com.strategy.application.port.inbound.outputdto.TacticCommentResponseDto;
import com.strategy.application.port.outbound.TacticCommentOutboundPort;
import com.strategy.application.port.outbound.TacticOutboundPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TacticCommentProcessor implements GetTacticCommentInboundPort, PostTacticCommentInboundPort {

    private final TacticCommentOutboundPort tacticCommentOutboundPort;
    private final TacticOutboundPort tacticOutboundPort;
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());


    public TacticCommentProcessor(TacticCommentOutboundPort tacticCommentOutboundPort, TacticOutboundPort tacticOutboundPort) {
        this.tacticCommentOutboundPort = tacticCommentOutboundPort;
        this.tacticOutboundPort = tacticOutboundPort;
    }

    @Override
    public List<TacticCommentResponseDto> getComments(Long tacticId) {
        return getTactic(tacticId).getTacticComments()
                .stream()
                .map(this::tacticCommentToCommentResponseDto)
                .collect(Collectors.toList());
    }

    private TacticCommentResponseDto tacticCommentToCommentResponseDto(TacticComment tacticComment) {
        if (tacticComment == null) {
            return null;
        }
        TacticCommentResponseDto.TacticCommentResponseDtoBuilder
                tacticCommentResponseDtoBuilder = TacticCommentResponseDto.builder();
        tacticCommentResponseDtoBuilder.username(tacticComment.getUsername());
        tacticCommentResponseDtoBuilder.contents(tacticComment.getContents());
        return tacticCommentResponseDtoBuilder.build();
    }


    @Override
    public void postComment(TacticCommentRequestDto tacticCommentRequestDto) {
        TacticComment tacticComment = TacticComment.builder()
                .contents(tacticCommentRequestDto.getContents())
                .username(tacticCommentRequestDto.getUsername())
                .tactic(getByReferenceTactic(tacticCommentRequestDto.getTacticId()))
                .build();
        try {
            tacticCommentOutboundPort.save(tacticComment);
        } catch (RuntimeException runtimeException){
            log.warn("tactic not exists");
            throw new NullPointerException("존재하지 않는 Tactic");
        };
    }

    private Tactic findTactic(Long tacticId){
        return tacticOutboundPort.findById(tacticId)
                .orElseThrow(() -> new NullPointerException("존재하지 않는 Tactic"));
    }

    private Tactic getByReferenceTactic(Long tacticId){
        return tacticOutboundPort.getReferenceById(tacticId);
    }

    private Tactic getTactic(Long tacticId) {
        return tacticOutboundPort.getById(tacticId);
    }
}
