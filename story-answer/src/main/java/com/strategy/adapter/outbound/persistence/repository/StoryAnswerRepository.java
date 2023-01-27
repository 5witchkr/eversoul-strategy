package com.strategy.adapter.outbound.persistence.repository;

import com.strategy.adapter.outbound.persistence.entity.StoryAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryAnswerRepository extends JpaRepository<StoryAnswer, Long> {
}
