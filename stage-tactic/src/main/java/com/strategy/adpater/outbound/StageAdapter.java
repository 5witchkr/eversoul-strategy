package com.strategy.adpater.outbound;


import com.strategy.adpater.outbound.persistence.entity.Stage;
import com.strategy.adpater.outbound.persistence.repository.StageRepository;
import com.strategy.application.port.outbound.StageOutboundPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StageAdapter implements StageOutboundPort {

    private final StageRepository stageRepository;

    public StageAdapter(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    @Override
    public Optional<Stage> getByLocationAndStep(String location, String step) {
        return stageRepository.getByLocationAndStep(location, step);
    }
}
