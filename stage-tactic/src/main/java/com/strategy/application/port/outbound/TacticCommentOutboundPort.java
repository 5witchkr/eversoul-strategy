package com.strategy.application.port.outbound;

import com.strategy.adpater.outbound.persistence.entity.TacticComment;

public interface TacticCommentOutboundPort {

    void save(TacticComment tacticComment);
}
