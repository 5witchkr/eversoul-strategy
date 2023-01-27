package com.strategy.application.port.outbound;

import com.strategy.adapter.outbound.persistence.entity.StoryQuestion;

import java.util.Optional;

public interface StoryQuestionOutboundPort {
    void save(StoryQuestion storyQuestion);

    void deleteById(Long id);

    Optional<StoryQuestion> findById(Long storyQuestionId);
}
