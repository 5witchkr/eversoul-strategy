package com.strategy.application.facade;

import com.strategy.application.port.inbound.outputdto.TierDto;
import com.strategy.application.port.inbound.SoulCharacterInboundPort;
import org.springframework.stereotype.Service;


@Service
public class SoulCharacterFacade implements SoulCharacterInboundPort {
    @Override
    public TierDto getTier(Long tier) {
        return null;
    }
}
