package com.strategy.adapter.outbound.persistence.jparepository;

import com.strategy.adapter.outbound.persistence.entity.SoulconnectBatchdata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoulconnectBatchdataRepository extends JpaRepository<SoulconnectBatchdata, Long> {

    List<SoulconnectBatchdata> findAllByTacticId(Long tacticId);

    List<SoulconnectBatchdata> findAllByIsBatched(int isBatched);
}
