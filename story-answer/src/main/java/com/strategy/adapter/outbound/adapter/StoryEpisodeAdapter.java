package com.strategy.adapter.outbound.adapter;


import com.strategy.adapter.outbound.persistence.entity.StoryEpisode;
import com.strategy.adapter.outbound.persistence.entity.StorySoulcharacter;
import com.strategy.adapter.outbound.persistence.repository.StoryEpisodeRepository;
import com.strategy.application.port.outbound.StoryEpisodeOutboundPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StoryEpisodeAdapter implements StoryEpisodeOutboundPort {

    private final StoryEpisodeRepository storyEpisodeRepository;

    public StoryEpisodeAdapter(StoryEpisodeRepository storyEpisodeRepository) {
        this.storyEpisodeRepository = storyEpisodeRepository;
    }

    @Override
    public StoryEpisode getByOrderNumberAndStorySoulcharacter(int orderNumber, StorySoulcharacter storySoulcharacter) {
        return storyEpisodeRepository.getByOrderNumberAndStorySoulcharacter(orderNumber, storySoulcharacter);
    }

    @Override
    public void save(StoryEpisode storyEpisode) {
        storyEpisodeRepository.save(storyEpisode);
    }

    @Override
    public Optional<StoryEpisode> findById(Long storyEpisodeId) {
        return storyEpisodeRepository.findById(storyEpisodeId);
    }

    @Override
    public void deleteById(Long id) {
        storyEpisodeRepository.deleteById(id);
    }

    @Override
    public StoryEpisode getByOrderNumberAndStorySoulcharacter_Id(int orderNumber, Long storySoulcharacterId) {
        return storyEpisodeRepository.getByOrderNumberAndStorySoulcharacter_Id(orderNumber, storySoulcharacterId);
    }
}
