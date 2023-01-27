package com.strategy.adapter.outbound.persistence.repository;

import com.strategy.adapter.outbound.persistence.entity.StoryEpisode;
import com.strategy.adapter.outbound.persistence.entity.StorySoulcharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryEpisodeRepository extends JpaRepository<StoryEpisode, Long> {

    StoryEpisode getByOrderNumberAndStorySoulcharacter(int orderNumber, StorySoulcharacter storySoulcharacter);


//    @Query("SELECT DISTINCT e FROM StoryEpisode e WHERE  " + "JOIN FETCH e.storyQuestions")
//    StoryEpisode getByOrderNumberAndStorySoulcharacterFetchJoin(int orderNumber, StorySoulcharacter storySoulcharacter);
}
