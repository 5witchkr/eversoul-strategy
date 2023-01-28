package com.strategy.application.validator;


import com.strategy.application.port.inbound.inputdto.SoulCharacterTacticRequestDto;
import com.strategy.application.port.inbound.inputdto.SoulCharacterTacticValidReqDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TacticSoulIdValidator {

    public List<SoulCharacterTacticValidReqDto> parseSoulNameToSoulId(
            List<SoulCharacterTacticRequestDto> soulCharacterTacticRequestDtos){
        return soulCharacterTacticRequestDtos.stream()
                .map(this::SoulNameDtoToSoulIdDto)
                .collect(Collectors.toList());
    }

    public SoulCharacterTacticValidReqDto SoulNameDtoToSoulIdDto(SoulCharacterTacticRequestDto soulCharacterTacticRequestDto) {
        return SoulCharacterTacticValidReqDto.builder()
                .soulId(NameToSoulId(soulCharacterTacticRequestDto.getName()))
                .level(soulCharacterTacticRequestDto.getLevel())
                .build();
    }

    private Long NameToSoulId(String soulName){
        return (long) (SoulNameConstants.allSoulNames.indexOf(soulName) + 1);
    }
}

