package com.strategy.adapter.outbound.persistence.repository;

import com.strategy.adapter.outbound.persistence.entity.StoryQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryQuestionRepository extends JpaRepository<StoryQuestion, Long> {
}
