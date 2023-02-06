package com.strategy.adapter.outbound.persistence.jparepository;

import com.strategy.adapter.outbound.persistence.entity.StatisticSoulconnect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatisticSoulConnectRepository extends JpaRepository<StatisticSoulconnect, Long> {
    Optional<StatisticSoulconnect> findByConnectSoulAndConnectedSoul(Long aLong, Long index);

    Optional<List<StatisticSoulconnect>> findTop10ByConnectSoul(Long connectSoul);

    Optional<List<StatisticSoulconnect>> findTop10ByConnectedSoul(Long connectedSoul);

    Optional<List<StatisticSoulconnect>> findTop10ByOrderByConnectCountDesc();
}
