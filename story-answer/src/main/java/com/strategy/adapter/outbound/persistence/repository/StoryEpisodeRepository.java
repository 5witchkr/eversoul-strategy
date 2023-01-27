package com.strategy.adapter.outbound.persistence.repository;

import com.strategy.adapter.outbound.persistence.entity.StoryEpisode;
import com.strategy.adapter.outbound.persistence.entity.StorySoulcharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryEpisodeRepository extends JpaRepository<StoryEpisode, Long> {

    StoryEpisode getByOrderNumberAndStorySoulcharacter(int orderNumber, StorySoulcharacter storySoulcharacter);

}
