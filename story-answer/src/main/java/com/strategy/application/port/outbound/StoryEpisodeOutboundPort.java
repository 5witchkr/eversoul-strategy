package com.strategy.application.port.outbound;

import com.strategy.adapter.outbound.persistence.entity.StoryEpisode;
import com.strategy.adapter.outbound.persistence.entity.StorySoulcharacter;

import java.util.Optional;

public interface StoryEpisodeOutboundPort {

    StoryEpisode getByOrderNumberAndStorySoulcharacter(int orderNumber, StorySoulcharacter storySoulcharacter);

    void save(StoryEpisode storyEpisode);

    Optional<StoryEpisode> findById(Long storyEpisodeId);

    void deleteById(Long id);
}
