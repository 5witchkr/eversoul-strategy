package com.strategy.adapter.outbound.persistence.repository;

import com.strategy.adapter.outbound.persistence.entity.StoryEpisode;
import com.strategy.adapter.outbound.persistence.entity.StorySoulcharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoryEpisodeRepository extends JpaRepository<StoryEpisode, Long> {

    Optional<StoryEpisode> getByOrderNumberAndStorySoulcharacter(int orderNumber, StorySoulcharacter storySoulcharacter);


    Optional<StoryEpisode> getByOrderNumberAndStorySoulcharacter_Id(int orderNumber, Long storySoulcharacterId);
}
