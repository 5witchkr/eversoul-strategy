package com.strategy.application.validator;


import com.strategy.application.port.inbound.inputdto.SoulCharacterTacticRequestDto;
import com.strategy.application.port.inbound.inputdto.SoulCharacterTacticValidReqDto;
import com.strategy.constantmodel.SoulNameConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SoulNameValidator {

    public void checkSoulName(String name){

        if (!SoulNameConstants.allSoulNames.contains(name)) throw new IllegalArgumentException("유효하지 않은 정령이름");
    }

    public void checkSoulNameByDtos(List<SoulCharacterTacticRequestDto> soulCharacters) {
        soulCharacters.forEach(dto -> checkSoulName(dto.getName()));
    }

    public void checkDuplicateSoul(List<SoulCharacterTacticValidReqDto> soulCharacters){
        if (soulCharacters.stream()
                .map(SoulCharacterTacticValidReqDto::getSoulId)
                .distinct()
                .count() != soulCharacters.size()){
            throw new RuntimeException("중복된 정령정보 입력");
        }
    }
}
