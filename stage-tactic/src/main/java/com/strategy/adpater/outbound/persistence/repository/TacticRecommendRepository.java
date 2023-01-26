package com.strategy.adpater.outbound.persistence.repository;

import com.strategy.adpater.outbound.persistence.entity.TacticRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacticRecommendRepository extends JpaRepository<TacticRecommend, Long> {
}
