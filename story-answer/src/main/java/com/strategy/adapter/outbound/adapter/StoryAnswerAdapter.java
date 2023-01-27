package com.strategy.adapter.outbound.adapter;

import com.strategy.adapter.outbound.persistence.entity.StoryAnswer;
import com.strategy.adapter.outbound.persistence.repository.StoryAnswerRepository;
import com.strategy.application.port.outbound.StoryAnswerOutboundPort;
import org.springframework.stereotype.Component;


@Component
public class StoryAnswerAdapter implements StoryAnswerOutboundPort {

    private final StoryAnswerRepository storyAnswerRepository;

    public StoryAnswerAdapter(StoryAnswerRepository storyAnswerRepository) {
        this.storyAnswerRepository = storyAnswerRepository;
    }

    @Override
    public void save(StoryAnswer storyAnswer) {
        storyAnswerRepository.save(storyAnswer);
    }

    @Override
    public void deleteById(Long id) {
        storyAnswerRepository.deleteById(id);
    }
}
