package com.strategy.application.validator;


import com.strategy.application.port.inbound.inputdto.souldto.SoulCharacterTacticRequestDto;
import com.strategy.application.port.inbound.inputdto.souldto.SoulCharacterTacticValidReqDto;
import com.strategy.enummodel.SoulIdEnum;
import com.strategy.constantmodel.SoulNameConstants;
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


    public void checkSoulId(List<SoulCharacterTacticValidReqDto> soulCharacters) {
        if (soulCharacters.stream()
                .allMatch(dto ->
                        SoulIdEnum.SOUL_ID_START.getValue()
                                <= dto.getSoulId() && dto.getSoulId() <=
                                SoulIdEnum.SOUL_ID_END.getValue())){
            return;
        }
        throw new IllegalArgumentException("유효하지 않은 정령정보");
    }
}

