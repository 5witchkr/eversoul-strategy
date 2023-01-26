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
    public Optional<Stage> getByLocationAndStep(int location, int step) {
        return stageRepository.getByLocationAndStep(location, step);
    }

    @Override
    public void deleteById(Long id) {
        stageRepository.deleteById(id);
    }

    @Override
    public void save(Stage stage) {
        stageRepository.save(stage);
    }

    @Override
    public Optional<Stage> findById(Long id) {
        return stageRepository.findById(id);
    }

    @Override
    public boolean existsByLocationAndStep(int location, int step) {
        return stageRepository.existsByLocationAndStep(location, step);
    }
}
