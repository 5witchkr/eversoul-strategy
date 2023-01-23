package com.strategy.application.processor;

import com.strategy.adapter.outbound.persistence.SoulCharacter;
import com.strategy.application.port.inbound.GetTierInboundPort;
import com.strategy.application.port.inbound.outputdto.SoulCharacterResponseDto;
import com.strategy.application.port.outbound.SoulCharacterOutboundPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
public class TierProcessor implements GetTierInboundPort {

    private final SoulCharacterOutboundPort soulCharacterOutboundPort;

    public TierProcessor(SoulCharacterOutboundPort soulCharacterOutboundPort) {
        this.soulCharacterOutboundPort = soulCharacterOutboundPort;
    }


    @Override
    public List<SoulCharacterResponseDto> getSoulByTier(String tier) {
        return Objects.requireNonNull(soulCharacterOutboundPort.getByTier(tier).orElse(null))
                .stream()
                .map(this::soulCharacterToSoulCharacterResponseDto)
                .collect(Collectors.toList());
    }

    private SoulCharacterResponseDto soulCharacterToSoulCharacterResponseDto(SoulCharacter soulCharacter){
        if (soulCharacter == null) {
            return null;
        }
        SoulCharacterResponseDto.SoulCharacterResponseDtoBuilder soulCharacterResponseDto = SoulCharacterResponseDto.builder();
        soulCharacterResponseDto.id(soulCharacter.getId());
        soulCharacterResponseDto.name(soulCharacter.getName());
        soulCharacterResponseDto.type(soulCharacter.getType());
        soulCharacterResponseDto.tier(soulCharacter.getTier());
        return soulCharacterResponseDto.build();
    }
}
