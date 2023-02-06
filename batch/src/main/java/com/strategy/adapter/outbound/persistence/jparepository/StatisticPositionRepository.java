package com.strategy.adapter.outbound.persistence.jparepository;

import com.strategy.adapter.outbound.persistence.entity.StatisticPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticPositionRepository extends JpaRepository<StatisticPosition, Long> {
}
