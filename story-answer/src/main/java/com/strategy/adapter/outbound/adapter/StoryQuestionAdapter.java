package com.strategy.adapter.outbound.adapter;


import com.strategy.adapter.outbound.persistence.entity.StoryQuestion;
import com.strategy.adapter.outbound.persistence.repository.StoryQuestionRepository;
import com.strategy.application.port.outbound.StoryQuestionOutboundPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StoryQuestionAdapter implements StoryQuestionOutboundPort {

    private final StoryQuestionRepository storyQuestionRepository;

    public StoryQuestionAdapter(StoryQuestionRepository storyQuestionRepository) {
        this.storyQuestionRepository = storyQuestionRepository;
    }

    @Override
    public void save(StoryQuestion storyQuestion) {
        storyQuestionRepository.save(storyQuestion);
    }

    @Override
    public void deleteById(Long id) {
        storyQuestionRepository.deleteById(id);
    }

    @Override
    public Optional<StoryQuestion> findById(Long storyQuestionId) {
        return storyQuestionRepository.findById(storyQuestionId);
    }
}
