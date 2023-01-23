package com.strategy.application.facade;

import com.strategy.application.port.inbound.outputdto.SoulCharacterResponseDto;
import com.strategy.application.processor.TierProcessor;
import com.strategy.application.validator.TierValidator;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SoulCharacterFacade {

    private final TierProcessor tierProcessor;
    private final TierValidator tierValidator;

    public SoulCharacterFacade(TierProcessor tierProcessor, TierValidator tierValidator) {
        this.tierProcessor = tierProcessor;
        this.tierValidator = tierValidator;
    }

    public List<SoulCharacterResponseDto> getSoulByTier(String tier) {
        tierValidator.checkInputValue(tier);
        return tierProcessor.getSoulByTier(tier);
    }
}
