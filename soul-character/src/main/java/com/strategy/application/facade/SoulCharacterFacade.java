package com.strategy.application.facade;

import com.strategy.application.port.inbound.GetTierInboundPort;
import com.strategy.application.port.inbound.outputdto.SoulCharacterResponseDto;
import com.strategy.application.validator.TierValidator;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SoulCharacterFacade implements SoulCharacterPortFacade {

    private final GetTierInboundPort getTierInboundPort;
    private final TierValidator tierValidator;

    public SoulCharacterFacade(GetTierInboundPort getTierInboundPort, TierValidator tierValidator) {
        this.getTierInboundPort = getTierInboundPort;
        this.tierValidator = tierValidator;
    }

    public List<SoulCharacterResponseDto> getSoulByTier(String tier) {
        tierValidator.checkInputValue(tier);
        return getTierInboundPort.getSoulByTier(tier);
    }
}
