package com.strategy.application.port.outbound;

import com.strategy.adpater.outbound.persistence.entity.Stage;

import java.util.Optional;

public interface StageOutboundPort {
    Optional<Stage> getByLocationAndStep(String location, String step);
}
