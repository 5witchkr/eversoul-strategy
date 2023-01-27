package com.strategy.application.port.outbound;

import com.strategy.adapter.outbound.persistence.entity.StoryAnswer;

public interface StoryAnswerOutboundPort {
    void save(StoryAnswer storyAnswer);

    void deleteById(Long id);
}
