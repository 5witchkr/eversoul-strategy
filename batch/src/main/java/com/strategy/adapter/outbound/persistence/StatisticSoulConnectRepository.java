package com.strategy.adapter.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatisticSoulConnectRepository extends JpaRepository<StatisticSoulconnect, Long> {
    Optional<StatisticSoulconnect> findByConnectSoulAndConnectedSoul(Long aLong, Long index);
}
