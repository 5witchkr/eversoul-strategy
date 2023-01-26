package com.strategy.adpater.outbound;

import com.strategy.adpater.outbound.persistence.entity.TacticComment;
import com.strategy.adpater.outbound.persistence.repository.TacticCommentRepository;
import com.strategy.application.port.outbound.TacticCommentOutboundPort;
import org.springframework.stereotype.Component;


@Component
public class TacticCommentAdapter implements TacticCommentOutboundPort {

    private final TacticCommentRepository tacticCommentRepository;

    public TacticCommentAdapter(TacticCommentRepository tacticCommentRepository) {
        this.tacticCommentRepository = tacticCommentRepository;
    }

    @Override
    public void save(TacticComment tacticComment) {
        tacticCommentRepository.save(tacticComment);
    }
}
