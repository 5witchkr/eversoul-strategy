package com.strategy.application.port.outbound;

import com.strategy.adapter.outbound.persistence.entity.StoryEpisode;
import com.strategy.adapter.outbound.persistence.entity.StorySoulcharacter;

import java.util.Optional;

public interface StoryEpisodeOutboundPort {

    Optional<StoryEpisode> getByOrderNumberAndStorySoulcharacter(int orderNumber, StorySoulcharacter storySoulcharacter);

    void save(StoryEpisode storyEpisode);

    Optional<StoryEpisode> findById(Long storyEpisodeId);

    void deleteById(Long id);


    /**
     * @deprecated May cause an "outer left join"
     * @param orderNumber
     * @param storySoulcharacterId
     * @return
     */
    Optional<StoryEpisode> getByOrderNumberAndStorySoulcharacter_Id(int orderNumber, Long storySoulcharacterId);
}
